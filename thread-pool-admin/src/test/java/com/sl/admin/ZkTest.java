package com.sl.admin;

import com.alibaba.fastjson.JSON;
import com.sl.core.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@SpringBootTest(classes = ThreadPoolAdminApplication.class)
public class ZkTest {


    @Value("${config.threadpool.appname}")
    private String appName;

    @Autowired
    private CuratorFramework zkClient;


    @Test
    void zkTest() {


        String pathNode = Constant.path + "/" + appName;
        List<String> nodeList = getChildNode(pathNode);
        log.info("nodeList:" + JSON.toJSONString(nodeList));


        nodeList.forEach(x -> {
            String detailPath = pathNode + "/" + x;
            String data = getZKData(detailPath);
            log.info("path:{},data:{}", detailPath, data);

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
