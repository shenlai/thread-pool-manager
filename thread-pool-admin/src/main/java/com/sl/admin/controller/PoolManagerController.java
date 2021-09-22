package com.sl.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.sl.admin.model.BaseResponse;
import com.sl.admin.model.ZkThreadPoolCofModel;
import com.sl.admin.service.PoolManagerService;
import com.sl.admin.vo.SearchPoolVO;
import com.sl.admin.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

/**
 *
 */
@Controller
public class PoolManagerController {


    @Autowired
    private PoolManagerService poolManagerService;


    @RequestMapping({"/template"})
    public String index() {
        return "layout";
    }

    /**
     * home page
     * @return
     */
    @RequestMapping({"/index", "/"})
    public String demoPage() {
        return "index";
    }


    /**
     * vue demo
     * @return
     */
    @RequestMapping("/poollist")
    public String poollist() {
        return "list";
    }

    @ResponseBody
    @RequestMapping("/getPoolList")
    public BaseResponse<ZkThreadPoolCofModel> getPoolList(@RequestBody SearchPoolVO searchVO) {
        if (searchVO == null) {
            return BaseResponse.fail("参数错误");
        }
        List<ZkThreadPoolCofModel> threadPoolCofList = poolManagerService.getThreadPoolCofList(searchVO.getAppName());
        return BaseResponse.success(threadPoolCofList);
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
