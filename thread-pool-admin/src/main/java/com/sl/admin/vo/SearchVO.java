package com.sl.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
public class SearchVO implements Serializable {
    private static final long serialVersionUID = 6820520682140485895L;
    private Long batchId;
    private Long productId;
    private String productName;
    private Long goodsId;
    private String goodsName;
    private String batchStatus;
    private Date createBefore;
    private Date createAfter;
    private Integer pageSize;
    private Integer currentPage;
}
