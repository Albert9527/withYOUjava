package com.zd1024.withyou.entityVo;

import lombok.Data;

import java.util.List;

@Data
public class ObjVo<T> {

    //当前页数
    private Integer current;

    //每页数据条数
    private Integer size;

    //总数据条数
    private Long total;

    private List<T> datalist;

    //总页数
    private Long pages;
}
