package com.j2ee.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultPageVo<T> {
    //返回提示信息
    private String msg;
    //返回码
    private int code;
    //当前第几页
    private int pageNum;
    //每页条数
    private int pageSize;
    //总条数
    private int total;
    //返回数据
    private T data;
}
