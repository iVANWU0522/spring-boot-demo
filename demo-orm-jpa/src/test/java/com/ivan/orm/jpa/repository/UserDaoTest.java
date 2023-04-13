package com.ivan.orm.jpa.repository;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.ivan.orm.jpa.SpringBootDemoOrmJpaApplicationTests;
import com.ivan.orm.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class UserDaoTest extends SpringBootDemoOrmJpaApplicationTests {
    @Autowired
    private UserDao userDao;

    @Test
    public void testSave() {
        String salt = IdUtil.fastSimpleUUID();
        User testSave3 = User.builder()
                .name("testSave3")
                .password(SecureUtil.md5("123456" + salt))
                .salt(salt)
                .email("testSave3@xkcoding.com")
                .phoneNumber("17300000003")
                .status(1)
                .lastLoginTime(new DateTime())
                .build();
        userDao.save(testSave3);

        assertNotNull(testSave3.getId());
        Optional<User> user = userDao.findById(testSave3.getId());
        assertTrue(user.isPresent());
        log.info("【user】= {}", user.get());
    }

    @Test
    public void testDelete() {
        User testSave3 = userDao.findUserByName("testSave3").get(0);
        long count = userDao.count();
        userDao.deleteById(testSave3.getId());
        long left = userDao.count();
        assertEquals(count - 1, left);
    }

    @Test
    public void testUpdate() {
        userDao.findById(2L).ifPresent(user -> {
            user.setName("testUpdate");
            userDao.save(user);
        });
        assertEquals("testUpdate", userDao.findById(2L).get().getName());
    }

    @Test
    public void testQueryOne() {
        Optional<User> user = userDao.findById(1L);
        assertTrue(user.isPresent());
        log.info("【user】= {}", user.get());
    }

    @Test
    public void testQueryAll() {
        List<User> users = userDao.findAll();
        assertNotEquals(0, users.size());
        log.debug("【users】= {}", users);
    }

    @Test
    public void testQueryByPage() {
        initData();
        Integer currentPage = 0;
        Integer pageSize = 5;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);
        Page<User> userPage = userDao.findAll(pageRequest);

        assertEquals(5, userPage.getSize());
        assertEquals(userDao.count(), userPage.getTotalElements());
        log.debug("【id】= {}", userPage.getContent().stream().map(User::getId).collect(Collectors.toList()));
    }

    private void initData() {
        List<User> userList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            String salt = IdUtil.fastSimpleUUID();
            int index = 3 + i;
            User user = User.builder()
                    .name("testSave" + index)
                    .password(SecureUtil.md5("123456" + salt))
                    .salt(salt)
                    .email("testSave" + index + "@xkcoding.com")
                    .phoneNumber("1730000000" + index)
                    .status(1)
                    .lastLoginTime(new DateTime())
                    .build();
            userList.add(user);
        }
        userDao.saveAll(userList);
    }
}
