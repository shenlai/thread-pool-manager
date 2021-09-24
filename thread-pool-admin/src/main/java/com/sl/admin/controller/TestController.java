package com.sl.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sl.admin.service.TestService;
import com.sl.admin.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * test
 */
@Slf4j
@RestController
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

    /**
     * vue demo
     *
     * @return
     */
    @RequestMapping("/poollist")
    public String poollist() {
        return "list";
    }


    @ResponseBody
    @RequestMapping("/data")
    public Object getData(@RequestBody SearchVO searchVO) {
        if (searchVO == null) {
            return null;
        }
        Integer start = (searchVO.getCurrentPage() - 1) * searchVO.getPageSize();
        Integer end = start + searchVO.getPageSize();
        List<Object> arr = array.subList(start, end > array.size() ? array.size() : end);
        return arr;
    }

    private static JSONArray array = JSON.parseArray("[\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    },\n" +
            "    {\n" +
            "        \"appName\": \"demoAPP\",\n" +
            "        \"threadPoolName\": \"taskPool\",\n" +
            "        \"coreSize\": 10,\n" +
            "        \"maxSize\": 20,\n" +
            "        \"keepAliveTime\": 3000,\n" +
            "        \"capacity\": 1000\n" +
            "    }\n" +
            "]");

}
