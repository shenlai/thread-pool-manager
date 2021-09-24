package com.sl.admin.model;

import lombok.Data;

import java.io.Serializable;

/**
 * zk  thread pool 配置
 *
 */
@Data
public class ZkThreadPoolCofModel  implements Serializable {
    private static final long serialVersionUID = -7272731817027176276L;

    private String appName;

    private String poolName;
    /**
     * coreSize
     */
    private Integer coreSize;

    /**
     * maxSize
     */
    private Integer maxSize;


    /**
     * capacity
     */
    private Integer capacity;

    /**
     * keepAliveTime
     */
    private Integer keepAliveTime;


}
