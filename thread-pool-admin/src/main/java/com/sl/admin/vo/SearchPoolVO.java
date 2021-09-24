package com.sl.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SearchPoolVO implements Serializable {
    private static final long serialVersionUID = 6820520682140485895L;
    private String appName;
    private String threadPoolName;
}
