package com.sl.admin;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@SpringBootApplication
public class ThreadPoolAdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ThreadPoolAdminApplication.class, args);

//        Object bean = context.getBean("test-thread-pool");
//
//        log.info("test-thread-pool instance:" + JSON.toJSONString(bean));


    }

}
