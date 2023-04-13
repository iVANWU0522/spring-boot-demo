package com.ivan.orm.jdbctemplate.dao.base;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ivan.orm.jdbctemplate.annotation.Column;
import com.ivan.orm.jdbctemplate.annotation.Ignore;
import com.ivan.orm.jdbctemplate.annotation.Pk;
import com.ivan.orm.jdbctemplate.annotation.Table;
import com.ivan.orm.jdbctemplate.constant.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class BaseDao<T, P> {
    private final JdbcTemplate jdbcTemplate;
    private final Class<T> entityClass;

    /**
     * Use this annotation to suppress warnings about unchecked casts.
     */
    @SuppressWarnings(value = "unchecked")
    public BaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        // it aims to get the generic type T in BaseDao<T, P>
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Integer insert(T t, Boolean ignoreNull) {
        String table = getTableName(t);

        List<Field> filterField = getField(t, ignoreNull);

        List<String> columnList = getColumns(filterField);

        String columns = StrUtil.join(Const.SEPARATOR_COMMA, columnList);

        String params = StrUtil.repeatAndJoin("?", columnList.size(), Const.SEPARATOR_COMMA);

        Object[] values = filterField.stream().map(field -> ReflectUtil.getFieldValue(t, field)).toArray();

        String sql = StrUtil.format("INSERT INTO {table} ({columns}) VALUES ({params})", Dict.create().set("table", table).set("columns", columns).set("params", params));
        log.debug("[Execute SQL]SQL：{}", sql);
        log.debug("[Execute SQL]Values：{}", JSONUtil.toJsonStr(values));
        return jdbcTemplate.update(sql, values);
    }

    protected Integer deleteById(P pk) {
        String tableName = getTableName();
        String sql = StrUtil.format("DELETE FROM {table} where id = ?", Dict.create().set("table", tableName));
        log.debug("[Execute SQL] SQL: {}", sql);
        log.debug("[Execute SQL] Id: {}", pk);
        return jdbcTemplate.update(sql, pk);
    }

    protected Integer updateById(T t, P pk, Boolean ignoreNull) {
        String tableName = getTableName(t);

        List<Field> filterField = getField(t, ignoreNull);

        List<String> columnList = getColumns(filterField);

        List<String> columns = columnList.stream().map(s -> StrUtil.appendIfMissing(s, " = ?")).collect(Collectors.toList());
        String params = StrUtil.join(Const.SEPARATOR_COMMA, columns);

        // 构造值
        List<Object> valueList = filterField.stream().map(field -> ReflectUtil.getFieldValue(t, field)).collect(Collectors.toList());
        valueList.add(pk);

        Object[] values = ArrayUtil.toArray(valueList, Object.class);

        String sql = StrUtil.format("UPDATE {table} SET {params} where id = ?", Dict.create().set("table", tableName).set("params", params));
        log.debug("[Execute SQL] SQL: {}", sql);
        log.debug("[Execute SQL] Id: {}", pk);
        log.debug("[Execute SQL] Values: {}", JSONUtil.toJsonStr(values));
        return jdbcTemplate.update(sql, values);
    }

    public T findOneById(P pk) {
        String tableName = getTableName();
        String sql = StrUtil.format("SELECT * FROM {table} where id = ?", Dict.create().set("table", tableName));
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<>(entityClass);
        log.debug("[Execute SQL] SQL: {}", sql);
        log.debug("[Execute SQL] Id: {}", pk);
        return jdbcTemplate.queryForObject(sql, new Object[]{pk}, rowMapper);
    }

    public List<T> findByExample(T t) {
        String tableName = getTableName(t);
        List<Field> filterField = getField(t, true);
        List<String> columnList = getColumns(filterField);

        List<String> columns = columnList.stream().map(s -> " and " + s + " = ? ").collect(Collectors.toList());

        String where = StrUtil.join(" ", columns);

        Object[] values = filterField.stream().map(field -> ReflectUtil.getFieldValue(t, field)).toArray();

        String sql = StrUtil.format("SELECT * FROM {table} where 1=1 {where}", Dict.create().set("table", tableName).set("where", StrUtil.isBlank(where) ? "" : where));
        log.debug("[Execute SQL] SQL: {}", sql);
        log.debug("[Execute SQL] Values: {}", JSONUtil.toJsonStr(values));
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, values);
        List<T> ret = CollUtil.newArrayList();
        maps.forEach(map -> ret.add(BeanUtil.fillBeanWithMap(map, ReflectUtil.newInstance(entityClass), true, false)));
        return ret;
    }

    private String getTableName(T t) {
        /*
         * It aims to get the table name from the annotation of the entity class,
         * for example, in `@Table(name = "orm_user")`, it get the "orm_user"
         */
        Table tableAnnotation = t.getClass().getAnnotation(Table.class);
        if (ObjectUtil.isNotNull(tableAnnotation)) {
            return StrUtil.format("`{}`", tableAnnotation.name());
        } else {
            return StrUtil.format("`{}`", t.getClass().getName().toLowerCase());
        }
    }

    private String getTableName() {
        Table tableAnnotation = entityClass.getAnnotation(Table.class);
        if (ObjectUtil.isNotNull(tableAnnotation)) {
            return StrUtil.format("`{}`", tableAnnotation.name());
        } else {
            return StrUtil.format("`{}`", entityClass.getName().toLowerCase());
        }
    }

    private List<String> getColumns(List<Field> fieldList) {
        List<String> columnList = CollUtil.newArrayList();
        for (Field field : fieldList) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName;
            if (ObjectUtil.isNotNull(columnAnnotation)) {
                columnName = columnAnnotation.name();
            } else {
                columnName = field.getName();
            }
            columnList.add(StrUtil.format("`{}`", columnName));
        }
        return columnList;
    }

    private List<Field> getField(T t, Boolean ignoreNull) {
        /*
         * It returns an array of all the declared fields of entity class,
         * The returned array includes both public and private fields.
         */
        Field[] fields = ReflectUtil.getFields(t.getClass());

        List<Field> filterField;
        /*
         * It aims to filter the fields which are annotated with `@Ignore` or `@Pk`
         */
        Stream<Field> fieldStream = CollUtil.toList(fields).stream().filter(field -> ObjectUtil.isNull(field.getAnnotation(Ignore.class)) || ObjectUtil.isNull(field.getAnnotation(Pk.class)));

        if (ignoreNull) {
            filterField = fieldStream.filter(field -> ObjectUtil.isNotNull(ReflectUtil.getFieldValue(t, field))).collect(Collectors.toList());
        } else {
            filterField = fieldStream.collect(Collectors.toList());
        }
        return filterField;
    }
}
