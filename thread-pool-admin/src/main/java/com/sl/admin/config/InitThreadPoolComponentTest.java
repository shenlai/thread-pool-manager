package com.sl.admin.config;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sl.admin.model.ThreadPoolConfigEntity;
import com.sl.admin.mapper.ThreadPoolConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
//@Component
public class InitThreadPoolComponentTest {

    @Resource
    private ThreadPoolConfigMapper threadPoolConfigMapper;

    @Autowired
    private ConfigurableListableBeanFactory configurableListableBeanFactory;


    @PostConstruct
    public void init() {
        initThreadPool();
    }

    public void initThreadPool() {

        LambdaQueryWrapper<ThreadPoolConfigEntity> queryWrapper = Wrappers
                .lambdaQuery(ThreadPoolConfigEntity.class)
                .eq(ThreadPoolConfigEntity::getDelFlag, 0);
        List<ThreadPoolConfigEntity> poolConfigEntityList = threadPoolConfigMapper.selectList(queryWrapper);
        try {

            poolConfigEntityList.forEach(x -> {
                //ThreadPoolTaskExecutor 封装了ThreadPoolExecutor
                if (!configurableListableBeanFactory.containsBean(x.getThreadPoolName())) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(x.getCoreSize(), x.getMaxSize(), x.getKeepAliveTime(), TimeUnit.MILLISECONDS,
                            new ArrayBlockingQueue(x.getCapacity()));
                    log.info("init threadPool name:{},info:{}", x.getThreadPoolName(), JSON.toJSONString(threadPoolExecutor));
                    configurableListableBeanFactory.registerSingleton(x.getThreadPoolName(), threadPoolExecutor);
                }
            });


        } catch (Exception e) {
            log.error("初始化线程池异常", e);
        }
    }

}
