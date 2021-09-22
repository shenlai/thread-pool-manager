package com.sl.admin.controller;

import com.sl.admin.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/testPool")
    public String testPool() throws InterruptedException {

        log.info("testPool method called");
        testService.testPool();
        log.info("testPool method exit");
        return "success";
    }


}
