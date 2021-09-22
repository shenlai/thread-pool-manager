package com.sl.admin.service;

import com.alibaba.fastjson.JSON;
import com.sl.admin.config.ZookeeperClient;
import com.sl.admin.model.ZkThreadPoolCofModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 线程池配置管理
 * zk
 */
@Service
public class PoolManagerService {

    private static final String pathRoot = "/threadpool";


    @Autowired
    private ZookeeperClient zookeeperClient;



    public List<ZkThreadPoolCofModel> getThreadPoolCofList(String appName) {
        List<ZkThreadPoolCofModel> retList = new ArrayList<>();
        String nodeParent = pathRoot + "/" + appName;
        List<String> childNodeList = zookeeperClient.getChildNode(nodeParent);

        childNodeList.forEach(x -> {

            String path = nodeParent + "/" + x;
            String nodeData = zookeeperClient.getNodeData(path);

            ZkThreadPoolCofModel cofModel = JSON.parseObject(nodeData, ZkThreadPoolCofModel.class);
            if (Objects.nonNull(cofModel)) {
                cofModel.setPoolName(x); //节点名 即 线程池名称
                retList.add(cofModel);
            }
        });

        return retList;
    }


}
