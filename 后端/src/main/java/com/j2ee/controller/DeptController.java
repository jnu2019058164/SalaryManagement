package com.j2ee.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.j2ee.common.ResultUtils;
import com.j2ee.common.ResultVo;
import com.j2ee.entity.*;
import com.j2ee.service.BsalService;
import com.j2ee.service.DeptService;
import com.j2ee.service.PosService;
import com.j2ee.vo.DeptParamVo;
import com.j2ee.vo.ParamVo;
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
@RequestMapping("/dept")
public class DeptController {

    //部门管理涉及三表操作
    @Autowired
    DeptService DeptData;
    @Autowired
    PosService PosData;
    @Autowired
    BsalService BSData;

    //根据部门名模糊查询
    @PostMapping("/DeptSearch")
    public ResultVo DeptSearch(@RequestBody DeptParamVo param){
        List<Dept4Boss> data_list = DeptData.SearchDept(param.getTitle());
        Page<Dept4Boss> data_Page = new Page<Dept4Boss>();
        //填充页数据
        data_Page.setRecords(data_list);
        data_Page.setSize(param.getPageSize());
        data_Page.setTotal(data_list.size());
        data_Page.setCurrent(param.getCurrentPage());

        return ResultUtils.success("输出成功",data_Page);
    }

    //部门信息添加
    @PostMapping("/DeptAdd")
    public ResultVo DeptAdd(@RequestBody Dept4Boss param) {
        //一个一个表插入

        //部门
        Dept dept = new Dept();
        dept.setDeptid(param.getDeptid());
        dept.setDeptname(param.getDeptname());
        if (DeptData.saveOrUpdate(dept)){}
        else{   return ResultUtils.error("部门插入失败"); }

        //岗位和底薪
        for (Pos i:param.getDeptPos()) {
            Bsal bsal = new Bsal();
            //岗位
            if(PosData.saveOrUpdate(i)){}
            else {return ResultUtils.error("岗位插入失败");}
            //底薪
            bsal.setPosid(i.getPosid());
            bsal.setBasesal(i.getBasesal());
            BSData.saveOrUpdate(bsal);
        }

        return ResultUtils.success("执行成功");
    }

    //部门信息修改
    @PostMapping("/DeptMod")
    public ResultVo DeptMod(@RequestBody Dept4Boss param){
        //一个一个表修改

        //部门
        Dept dept = new Dept();
        dept.setDeptid(param.getDeptid());
        dept.setDeptname(param.getDeptname());
        if (DeptData.updateById(dept)){}
        else{   return ResultUtils.error("部门插入失败"); }

        //岗位和底薪
        for (Pos i:param.getDeptPos()) {
            Bsal bsal = new Bsal();
            //岗位
            if(PosData.updateById(i)){}
            else {return ResultUtils.error("岗位插入失败");}
            //底薪
            bsal.setPosid(i.getPosid());
            bsal.setBasesal(i.getBasesal());
            BSData.updateById(bsal);
        }

        return ResultUtils.success("执行成功");
    }

    //部门信息删除
    @PostMapping("/DeptDel")
    public ResultVo DeptDel(@RequestBody Dept4Boss param){
        //一个一个表删除

        //部门
        Dept dept = new Dept();
        dept.setDeptid(param.getDeptid());
        dept.setDeptname(param.getDeptname());
        if (DeptData.removeById(dept)){}
        else{   return ResultUtils.error("部门插入失败"); }

        //岗位和底薪
        for (Pos i:param.getDeptPos()) {
            Bsal bsal = new Bsal();
            //岗位
            if(PosData.removeById(i)){}
            else {return ResultUtils.error("岗位插入失败");}
            //底薪
            bsal.setPosid(i.getPosid());
            bsal.setBasesal(i.getBasesal());
            BSData.removeById(bsal);
            //薪资项

        }

        return ResultUtils.success("执行成功");
    }
}

