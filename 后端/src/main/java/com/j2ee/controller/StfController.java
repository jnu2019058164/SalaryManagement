package com.j2ee.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.j2ee.common.ResultUtils;
import com.j2ee.common.ResultVo;
import com.j2ee.entity.Stf;
import com.j2ee.service.StfService;
import com.j2ee.vo.StfParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author : 陈辉煌_CV（Chan Vivon)
 * @since 2022-11-19
 */
@RestController
@RequestMapping("/stf")
public class StfController {

    //服务层初始化
    @Autowired
    StfService StfData;

    //员工信息添加
    @PostMapping("/StfAdd")
    public ResultVo StfAdd(@RequestBody Stf param){
        param.setEntrydate(new Date());
        boolean success=false;
        try{
            System.out.println(param);
            success = StfData.save(param);
        }
        catch (Exception e){
            System.out.println(e.toString());
            return ResultUtils.error("输出失败");
        }
        finally {
            if (success) {
                return ResultUtils.error("输出成功");
            }
            else {
                return ResultUtils.error("输出失败");
            }
        }
    }

    //员工信息修改
    @PostMapping("/StfMod")
    public ResultVo StfMod(@RequestBody Stf param) throws Exception {
        if (StfData.updateById(param)) {
            return ResultUtils.success("输出成功", param);
        } else {
            return ResultUtils.error("输出失败", param);
        }
    }

    //员工信息查询(使用Page类实现翻页）
    @PostMapping("/SearchStf")
    public ResultVo SearchList(@RequestBody StfParamVo param) throws Exception{
        List<Stf> Temp_Stf = StfData.SearchStf(param.getTitle());
        Page<Stf> Stf_Page = new Page<Stf>();
        //Page类设置数据、页大小、总大小与当前页
        Stf_Page.setRecords(Temp_Stf);
        Stf_Page.setSize(param.getPageSize());
        Stf_Page.setTotal(Temp_Stf.size());
        Stf_Page.setCurrent(param.getCurrentPage());
        //调试用的
//        System.out.println("parameter: "+param.getTitle());
        System.out.println(Stf_Page.getSize());
        System.out.println(Stf_Page.getCurrent());
        System.out.println(Stf_Page.getTotal());
        System.out.println(Temp_Stf.get(0).getEntrydate());
        return ResultUtils.success("输出成功",Stf_Page);
    }

    //删除员工信息
    @PostMapping("/StfDel")
    public ResultVo StfDel(@RequestBody Stf param) throws Exception {
        if (StfData.removeById(param)) {
            return ResultUtils.success("输出成功", param);
        } else {
            return ResultUtils.error("输出失败", param);
        }
    }
}

