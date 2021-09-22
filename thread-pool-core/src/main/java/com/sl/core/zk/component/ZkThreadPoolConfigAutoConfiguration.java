package com.sl.core.zk.component;


import com.sl.core.Constant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(name = Constant.zk_enable, havingValue = "true")
@Import({ZookeeperConfig.class, InitThreadPoolComponent.class, UpdateThreadPoolComponent.class})
public class ZkThreadPoolConfigAutoConfiguration {


}
