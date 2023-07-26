package com.j2ee.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultVo<T>{
    //返回状态码
    private int code;
    //返回提示信息
    private String msg;
    //返回数据
    private T data;
}
