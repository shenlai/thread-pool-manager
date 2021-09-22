package com.sl.admin;

import com.alibaba.fastjson.JSON;
import com.sl.admin.model.ThreadPoolConfigEntity;
import com.sl.admin.mapper.ThreadPoolConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = ThreadPoolAdminApplication.class)
class ThreadPoolAdminApplicationTests {


    @Autowired
    private ThreadPoolConfigMapper threadPoolConfigMapper;

    @Test
    void contextLoads() {
//        LambdaQueryWrapper<ItemInfo> queryWrapper = Wrappers
//                .lambdaQuery(ItemInfo.class)
//                .eq(ItemInfo::getTenantId, tenantId)
//                .eq(ItemInfo::getItemId, itemId);

        ThreadPoolConfigEntity threadPoolConfig = threadPoolConfigMapper.selectById(1);
        log.info(JSON.toJSONString(threadPoolConfig));



    }

}
