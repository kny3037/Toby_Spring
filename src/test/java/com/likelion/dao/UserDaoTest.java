package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;
    UserDao userDao;

    User user1;
    User user2;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        this.userDao = context.getBean("awsUserDao", UserDao.class);
        this.user1 = new User("1","nayeong","1111");
        this.user2 = new User("2","kate","2222");
        }

    @Test
    void addAndfindById() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        userDao.add(user1);
        userDao.add(user2);
        userDao.findById("2");
        assertEquals(2,userDao.getCount());
        //assertEquals(2,userDao.getCount());
        // ->assertEquals(x,y); : 객체 x와 y가 일치함을 확인
        //x는 예상 값, y는 실제 값이며 둘의 값이 같으면 테스트 통과!
    }

    @Test
    void getAllTest() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        userDao.add(user1);
        userDao.add(user2);
        assertEquals(2, userDao.getAll().size());
    }

}