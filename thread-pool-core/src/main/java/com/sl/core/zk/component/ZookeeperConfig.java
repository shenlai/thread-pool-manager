package com.sl.core.zk.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ZookeeperConfig {

    @Value("${config.zookeeper.address}")
    private String connectString;

    @Value("${config.zookeeper.timeout}")
    private int timeout;


    @Bean(name = "zkClient")
    public  CuratorFramework getInstance() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.
                newClient(connectString, timeout, 5000,
                        new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();
        return curatorFramework;
    }


//    @Bean(name = "zkClient")
//    public ZooKeeper zkClient() {
//        ZooKeeper zooKeeper = null;
//        try {
//            final CountDownLatch countDownLatch = new CountDownLatch(1);
//            //连接成功后，会回调watcher监听，此连接操作是异步的，执行完new语句后，直接调用后续代码
//            //  可指定多台服务地址 127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183
//            zooKeeper = new ZooKeeper(connectString, timeout, new Watcher() {
//                @Override
//                public void process(WatchedEvent event) {
//                    if (Event.KeeperState.SyncConnected == event.getState()) {
//                        //如果收到了服务端的响应事件,连接成功
//                        countDownLatch.countDown();
//                    }
//                }
//            });
//            countDownLatch.await();
//            log.info("【初始化ZooKeeper连接状态....】={}", zooKeeper.getState());
//
//        } catch (Exception e) {
//            log.error("初始化ZooKeeper连接异常....】={}", e);
//        }
//        return zooKeeper;
//    }

}
