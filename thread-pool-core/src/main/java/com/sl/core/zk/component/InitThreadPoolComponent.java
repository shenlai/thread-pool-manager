package com.sl.core.zk.component;


import com.alibaba.fastjson.JSON;
import com.sl.core.Constant;
import com.sl.core.zk.model.ThreadPoolConfigModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class InitThreadPoolComponent {

    @Value("${config.threadpool.appname}")
    private String appName;

    @Autowired
    private CuratorFramework zkClient;


    @Autowired
    private ConfigurableListableBeanFactory configurableListableBeanFactory;


    @PostConstruct
    public void init() {
        String pathNode = Constant.path + "/" + appName;
        List<String> nodeList = getChildNode(pathNode);
        log.info("nodeList:" + JSON.toJSONString(nodeList));

        Map<String, ThreadPoolConfigModel> threadPoolConfigModelMap = new HashMap<>();

        nodeList.forEach(x -> {
            String detailPath = pathNode + "/" + x;
            String configData = getZKData(detailPath);
            log.info("path:{},data:{}", detailPath, configData);
            ThreadPoolConfigModel poolConfigModel = JSON.parseObject(configData, ThreadPoolConfigModel.class);
            threadPoolConfigModelMap.putIfAbsent(x,poolConfigModel);
        });

        threadPoolConfigModelMap.forEach((key, value) -> {
            if (!configurableListableBeanFactory.containsBean(key)) {
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(value.getCoreSize(), value.getMaxSize(), value.getKeepAliveTime(), TimeUnit.MILLISECONDS,
                        new ArrayBlockingQueue(value.getCapacity()));
                log.info("init threadPool name:{},info:{}", key, JSON.toJSONString(threadPoolExecutor));
                configurableListableBeanFactory.registerSingleton(key, threadPoolExecutor);
            }
        });

    }


    public List<String> getChildNode(String parentPath) {
        List<String> nodeList = new ArrayList<>();
        try {
            Stat stat = zkClient.checkExists().forPath(parentPath);
            if (Objects.isNull(stat)) {
                return null;
            }
            nodeList = zkClient.getChildren().forPath(parentPath);
        } catch (Exception e) {
            log.error("getZKData exception", e);
        }
        return nodeList;
    }


    public String getZKData(String path) {
        String data = null;
        try {
            Stat stat = zkClient.checkExists().forPath(path);
            if (Objects.isNull(stat)) {
                return null;
            }
            data = new String(zkClient.getData().forPath(path));
        } catch (Exception e) {
            log.error("getZKData exception", e);
        }
        return data;
    }

}
