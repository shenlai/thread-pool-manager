package com.sl.core.zk.component;

import com.alibaba.fastjson.JSON;
import com.sl.core.Constant;
import com.sl.core.zk.model.ThreadPoolConfigModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class UpdateThreadPoolComponent implements ApplicationRunner {


    @Value("${config.threadpool.appname}")
    private String appName;

    @Autowired
    private CuratorFramework zkClient;

    @Autowired
    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String watchBasePath = Constant.path + "/" + appName;
        //TreeCache treeCache = new TreeCache(zkClient, watchBasePath);
        PathChildrenCache treeCache = new PathChildrenCache(zkClient, watchBasePath, true);
        treeCache.start();  //开启监听
        treeCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("node changed,type:" + event.getType() + ",event:" + JSON.toJSONString(event));

                updateThreadPoolConfig(event);
            }
        });


    }

    private void updateThreadPoolConfig(PathChildrenCacheEvent event) {
        switch (event.getType()) {
            case CHILD_ADDED:{
                String configData = new String(event.getData().getData());
                String threadPoolName = event.getData().getPath().substring(event.getData().getPath().lastIndexOf("/") + 1);
                ThreadPoolConfigModel poolConfigModel = JSON.parseObject(configData, ThreadPoolConfigModel.class);
                if(Objects.isNull(poolConfigModel)){
                    log.info(" name: {} config value is null", threadPoolName);
                   break;
                }

                if (!configurableListableBeanFactory.containsBean(threadPoolName)) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolConfigModel.getCoreSize(), poolConfigModel.getMaxSize(), poolConfigModel.getKeepAliveTime(), TimeUnit.MILLISECONDS,
                            new ArrayBlockingQueue(poolConfigModel.getCapacity()));
                    log.info("init threadPool name:{},info:{}", threadPoolName, JSON.toJSONString(threadPoolExecutor));
                    configurableListableBeanFactory.registerSingleton(threadPoolName, threadPoolExecutor);
                }else{
                    log.info(" name: {} threadPool exist!!", threadPoolName);
                }
                break;
            }
            case CHILD_UPDATED: {
                String configData = new String(event.getData().getData());
                String threadPoolName = event.getData().getPath().substring(event.getData().getPath().lastIndexOf("/") + 1);
                ThreadPoolConfigModel poolConfigModel = JSON.parseObject(configData, ThreadPoolConfigModel.class);
                if (!configurableListableBeanFactory.containsBean(threadPoolName)) {
                    //init
                    log.info("{}:threadpool not exist. configInfo:{}", threadPoolName, configData);
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolConfigModel.getCoreSize(), poolConfigModel.getMaxSize(), poolConfigModel.getKeepAliveTime(), TimeUnit.MILLISECONDS,
                            new ArrayBlockingQueue(poolConfigModel.getCapacity()));
                    log.info("init threadPool name:{},info:{}", threadPoolName, JSON.toJSONString(threadPoolExecutor));
                    configurableListableBeanFactory.registerSingleton(threadPoolName, threadPoolExecutor);
                }else{
                    //update
                    log.info("节点数据:{}", configData);
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) configurableListableBeanFactory.getBean(threadPoolName);
                    log.info("before updated threadPool name:{},info:{}", threadPoolName, JSON.toJSONString(threadPoolExecutor));

                    threadPoolExecutor.setCorePoolSize(poolConfigModel.getCoreSize());
                    threadPoolExecutor.setMaximumPoolSize(poolConfigModel.getMaxSize());
                    threadPoolExecutor.setKeepAliveTime(poolConfigModel.getKeepAliveTime(), TimeUnit.MILLISECONDS); //TODO  TimeUnit 参数化
                    //threadPoolExecutor.setRejectedExecutionHandler();   //TODO 拒绝策略
                    //poolConfigModel.getCapacity();     //TODO 队列设置
                    log.info("after updated threadPool name:{},info:{}", threadPoolName, JSON.toJSONString(threadPoolExecutor));
                }

                break;
            }
            default: {
                break;
            }


        }


    }


}
