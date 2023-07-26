package com.j2ee.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.j2ee.common.ResultUtils;
import com.j2ee.common.ResultVo;
import com.j2ee.service.AcctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * author ：当然是我陈辉煌
 * since 2022-11-19
 */
@CrossOrigin
@RestController
@RequestMapping("/acct")
public class AcctController {
    @Autowired
    AcctService AcctS;
    
    @PostMapping("/search")
    public ResultVo Search(){
        return ResultUtils.success("输出成功",AcctS.list());
    }
}

