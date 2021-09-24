package com.sl.admin.service;

import com.alibaba.fastjson.JSON;
import com.sl.admin.config.ZookeeperClient;
import com.sl.admin.model.ZkThreadPoolCofModel;
import org.apache.zookeeper.CreateMode;
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
                cofModel.setAppName(appName);
                retList.add(cofModel);
            }
        });

        return retList;
    }

    public ZkThreadPoolCofModel getThreadPoolCof(String appName, String threadPoolName) {
        List<ZkThreadPoolCofModel> retList = new ArrayList<>();
        String nodePath = pathRoot + "/" + appName + "/" + threadPoolName;
        String nodeData = zookeeperClient.getNodeData(nodePath);

        ZkThreadPoolCofModel cofModel = JSON.parseObject(nodeData, ZkThreadPoolCofModel.class);
        if (Objects.nonNull(cofModel)) {
            cofModel.setPoolName(threadPoolName); //节点名 即 线程池名称
            cofModel.setAppName(appName);
            retList.add(cofModel);
        }
        return cofModel;
    }

    public boolean saveThreadPoolCof(ZkThreadPoolCofModel model) {
        boolean ret = false;
        List<ZkThreadPoolCofModel> retList = new ArrayList<>();
        String nodePath = pathRoot + "/" + model.getAppName() + "/" + model.getPoolName();
        if (zookeeperClient.isExistNode(nodePath)) {
            //update
            ret = zookeeperClient.updateNodeData(nodePath, JSON.toJSONString(model));
        } else {
            //add
            ret = zookeeperClient.crateNode(nodePath, CreateMode.PERSISTENT, JSON.toJSONString(model));
        }
        return ret;
    }


}
