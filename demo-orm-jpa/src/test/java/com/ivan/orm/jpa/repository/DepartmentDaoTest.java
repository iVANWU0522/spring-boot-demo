package com.ivan.orm.jpa.repository;

import cn.hutool.json.JSONUtil;
import com.ivan.orm.jpa.SpringBootDemoOrmJpaApplicationTests;
import com.ivan.orm.jpa.entity.Department;
import com.ivan.orm.jpa.entity.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Slf4j
public class DepartmentDaoTest extends SpringBootDemoOrmJpaApplicationTests {
    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void testSave() {
        Collection<Department> departmentList = departmentDao.findDepartmentsByLevels(0);

        if (departmentList.isEmpty()) {
            Department testSave1 = Department.builder()
                    .name("testSave1")
                    .orderNo(0)
                    .levels(0)
                    .superior(null)
                    .build();

            Department testSave1_1 = Department.builder()
                    .name("testSave1_1")
                    .orderNo(0)
                    .levels(1)
                    .superior(testSave1)
                    .build();

            Department testSave1_2 = Department.builder()
                    .name("testSave1_2")
                    .orderNo(0)
                    .levels(1)
                    .superior(testSave1)
                    .build();

            Department testSave1_1_1 = Department.builder()
                    .name("testSave1_1_1")
                    .orderNo(0)
                    .levels(2)
                    .superior(testSave1_1)
                    .build();

            departmentList.add(testSave1);
            departmentList.add(testSave1_1);
            departmentList.add(testSave1_2);
            departmentList.add(testSave1_1_1);
            departmentDao.saveAll(departmentList);

            Collection<Department> departmentAll = departmentDao.findAll();
            log.info("【departmentAll】= {}", JSONArray.toJSONString((List) departmentAll));
        }

        userDao.findById(1L).ifPresent(user -> {
            user.setName("addDepartment");
            user.setDepartmentList(departmentList);
            userDao.save(user);
        });

        log.debug("User departments: {}", JSONUtil.toJsonStr(userDao.findById(1L).get().getDepartmentList()));

        departmentDao.findById(2L).ifPresent(dept -> {
            Collection<User> userlist = dept.getUserList();
            log.debug("Department's users={}", JSONUtil.toJsonStr(userlist));
        });


        userDao.findById(1L).ifPresent(user -> {
            user.setName("clearDepartments");
            user.setDepartmentList(null);
            userDao.save(user);
        });
        log.debug("User departments={}", userDao.findById(1L).get().getDepartmentList());
    }

}
