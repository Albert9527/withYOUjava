package com.zd1024.withyou;

import com.zd1024.withyou.entity.User;
import com.zd1024.withyou.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class WithyouApplicationTests {

    @Test
    void contextLoads() {
    }

}

@SpringBootTest
class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        /* Assert.assertEquals(5, userList.size());*/
        userList.forEach(System.out::println);
    }

    @Test
    public void test() {
        String u1 = UUID.randomUUID().toString();
        String u2 = UUID.randomUUID().toString();
        System.out.println("》》》》》"+u1);
        System.out.println("》》》》》"+u2);
    }

}


