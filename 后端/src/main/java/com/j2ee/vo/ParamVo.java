package com.j2ee.vo;

import lombok.Data;

@Data
public class ParamVo {
    //每页显示的条数
    private int pageSize;
    //当前页
    private int currentPage;
}
