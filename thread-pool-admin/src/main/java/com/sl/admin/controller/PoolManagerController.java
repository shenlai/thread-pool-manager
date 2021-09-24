package com.sl.admin.controller;

import com.sl.admin.model.BaseResponse;
import com.sl.admin.model.ZkThreadPoolCofModel;
import com.sl.admin.service.PoolManagerService;
import com.sl.admin.vo.SearchPoolVO;
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

    /**
     * home page
     *
     * @return
     */
    @RequestMapping({"/index", "/"})
    public String demoPage() {
        return "index";
    }


    @RequestMapping({"/template"})
    public String index() {
        return "layout";
    }


    @ResponseBody
    @RequestMapping("/getPoolList")
    public BaseResponse<List<ZkThreadPoolCofModel>> getPoolList(@RequestBody SearchPoolVO searchVO) {
        if (searchVO == null) {
            return BaseResponse.fail("参数错误");
        }
        List<ZkThreadPoolCofModel> threadPoolCofList = poolManagerService.getThreadPoolCofList(searchVO.getAppName());
        return BaseResponse.success(threadPoolCofList);
    }

    @ResponseBody
    @RequestMapping("/getPoolInfo")
    public BaseResponse<ZkThreadPoolCofModel> getPoolInfo(@RequestBody SearchPoolVO searchVO) {
        if (searchVO == null) {
            return BaseResponse.fail("参数错误");
        }
        ZkThreadPoolCofModel threadPoolCof = poolManagerService.getThreadPoolCof(searchVO.getAppName(), searchVO.getThreadPoolName());
        return BaseResponse.success(threadPoolCof);
    }

    @ResponseBody
    @RequestMapping("/savePoolInfo")
    public BaseResponse getPoolInfo(@RequestBody ZkThreadPoolCofModel model) {
        if (model == null) {
            return BaseResponse.fail("参数错误");
        }
        boolean b = poolManagerService.saveThreadPoolCof(model);
        return b ? BaseResponse.success(true) : BaseResponse.fail("保存失败");
    }


}
