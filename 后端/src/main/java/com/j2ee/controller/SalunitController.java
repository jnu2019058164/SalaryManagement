package com.j2ee.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.j2ee.common.ResultUtils;
import com.j2ee.common.ResultVo;
import com.j2ee.entity.Dept4Boss;
import com.j2ee.entity.Sal;
import com.j2ee.entity.Salunit;
import com.j2ee.entity.Stf;
import com.j2ee.service.SalunitService;
import com.j2ee.vo.SalParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CV
 * @since 2022-11-19
 */
@RestController
@RequestMapping("/salunit")
public class SalunitController {

    @Autowired
    SalunitService SalData;

    //加薪
    @PostMapping("/SalAdd")
    public ResultVo SalAdd(@RequestBody Salunit param) throws Exception {
        if (SalData.saveOrUpdate(param)) {
            return ResultUtils.success("薪资项添加成功");
        } else {
            return ResultUtils.error("薪资项添加失败");
        }
    }

    //模糊查询某个员工薪水
    @PostMapping("/SalSearch")
    public ResultVo SalSearch(@RequestBody SalParamVo param) throws Exception {
        List<Salunit> data_list = SalData.SearchSUnit(param.getTitle());
        Page<Salunit> data_Page = new Page<Salunit>();
        //填充页数据
        data_Page.setRecords(data_list);
        data_Page.setSize(param.getPageSize());
        data_Page.setTotal(data_list.size());
        data_Page.setCurrent(param.getCurrentPage());

        return ResultUtils.success("输出成功",data_Page);
    }

    //删除加薪情况
    @PostMapping("/SalDel")
    public ResultVo StfDel(@RequestBody Salunit param) throws Exception {
        if (SalData.removeById(param)) {
            return ResultUtils.success("薪资项删除成功");
        } else {
            return ResultUtils.error("薪资项删除失败");
        }
    }

    //修改加薪情况
    @PostMapping("/SalMod")
    public ResultVo StfMod(@RequestBody Salunit param) throws Exception {
        if (SalData.updateById(param)) {
            return ResultUtils.success("薪资项修改成功");
        } else {
            return ResultUtils.error("薪资项修改失败");
        }
    }
}

