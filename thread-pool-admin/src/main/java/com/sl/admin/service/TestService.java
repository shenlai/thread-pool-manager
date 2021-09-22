package com.sl.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.xml.ws.ServiceMode;

@Slf4j
@Service
public class TestService {


    @Async("pooldemo")
    public void testPool() throws InterruptedException {
        log.info("testPool  begin");
        Thread.sleep(5000);
        log.info("testPool  end");
        //return "success";
    }

}
