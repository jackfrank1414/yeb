package com.jackfrank.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : jackfrank
 * @version : v1.0
 * @description : 项目启动类
 * @createTime : 2022/8/28 14:34
 */
@SpringBootApplication
@MapperScan("com.jackfrank.server.mapper")
public class YebApplication {
    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class, args);
    }
}
