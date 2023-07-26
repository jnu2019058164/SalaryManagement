package com.j2ee.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.j2ee.common.ResultUtils;
import com.j2ee.common.ResultVo;
import com.j2ee.entity.Salary;
import com.j2ee.entity.Salunit;
import com.j2ee.mapper.SalaryMapper;
import com.j2ee.service.AcctService;
import com.j2ee.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * author ：当然是我陈辉煌
 * since 2022-11-19
 */

@RestController
@RequestMapping("/Report")
public class SalaryController {
    @Autowired
    SalaryService SalaryData;

    //去Oracle建个存储过程求薪水
    @PostMapping("/salform")
    public ResultVo salform() throws Exception {
        return ResultUtils.success("成功",SalaryData.test(1));
    }
}