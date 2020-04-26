package com.zd1024.withyou.entityVo;

import lombok.Data;

import java.util.List;

@Data
public class AndroidData<T>{

    private boolean success;

    private String msg;

    private List<T> datalist;

    private T data;
}
