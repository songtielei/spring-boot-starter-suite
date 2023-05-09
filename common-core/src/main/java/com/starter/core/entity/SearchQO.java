package com.starter.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@ApiModel("查询条件构造对象")
@ToString
@Data
public class SearchQO implements Serializable {

    @ApiModelProperty("过滤条件")
    private SearchFilter filter;

    @ApiModelProperty("排序字段")
    private SearchSort searchSort;

    @ApiModelProperty("当前页数")
    private Integer pageNo;

    @ApiModelProperty("每页数量")
    private Integer pageSize;

}
