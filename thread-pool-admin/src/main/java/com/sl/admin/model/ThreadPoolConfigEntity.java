package com.sl.admin.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("thread_pool_config")
public class ThreadPoolConfigEntity {



    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * threadPoolName
     */
    private String threadPoolName;

    /**
     * coreSize
     */
    private Integer coreSize;

    /**
     * maxSize
     */
    private Integer maxSize;

    /**
     * queueType
     */
    private Integer queueType;

    /**
     * capacity
     */
    private Integer capacity;

    /**
     * keepAliveTime
     */
    private Integer keepAliveTime;

    /**
     * rejectedType
     */
    private Integer rejectedType;

    /**
     * isAlarm
     */
    private Integer isAlarm;

    /**
     * capacityAlarm
     */
    private Integer capacityAlarm;

    /**
     * livenessAlarm
     */
    private Integer livenessAlarm;

    /**
     * content
     */
    @JSONField(serialize = false)
    private String content;


    /**
     * desc
     */
    @JSONField(serialize = false)
    @TableField(exist = false, fill = FieldFill.UPDATE)
    private String desc;

    /**
     * gmtCreate
     */
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * gmtModified
     */
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * delFlag
     */
    @TableLogic
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;

}
