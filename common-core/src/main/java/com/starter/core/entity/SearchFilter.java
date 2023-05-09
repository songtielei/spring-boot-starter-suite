package com.starter.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ApiModel("SearchFilter")
@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilter implements Serializable {

    @ApiModelProperty("逻辑关系")
    private Logic logic;

    @ApiModelProperty("子过滤对象")
    private List<SearchFilter> filters;


    @ApiModelProperty("过滤字段")
    private String field;

    @ApiModelProperty("操作符")
    private Operator operator;

    @ApiModelProperty("查询条件值")
    private String value;

    public enum Logic {
        and(),
        or()
    }

    public enum Operator {
        isNull(),
        notNull(),

        empty(),
        notEmpty(),

        eq(),
        neq(),

        gte(),
        lte(),

        gt(),
        lt(),

        start(),
        contain(),
        notContain(),

        between(),
        notBetween(),

        in(),
        notIn(),

    }

}


