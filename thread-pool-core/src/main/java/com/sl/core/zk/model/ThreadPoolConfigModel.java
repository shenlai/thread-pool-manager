package com.sl.core.zk.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ThreadPoolConfigModel implements Serializable {


    private static final long serialVersionUID = -6871791256711908488L;

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
