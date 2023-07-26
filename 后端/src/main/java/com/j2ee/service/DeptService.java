package com.j2ee.service;

import com.j2ee.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.j2ee.entity.Dept4Boss;

import java.util.List;

/**
 * @author CV
 * @since 2022-11-19
 */
public interface DeptService extends IService<Dept> {
    List<Dept4Boss> SearchDept(String kw);

}
