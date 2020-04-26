package com.zd1024.withyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zd1024.withyou.dao")
public class WithyouApplication {

    public static void main(String[] args) {
        SpringApplication.run(WithyouApplication.class, args);
    }

}
