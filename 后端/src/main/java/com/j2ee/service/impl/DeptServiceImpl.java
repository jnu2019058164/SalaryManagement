package com.j2ee.service.impl;

import com.j2ee.entity.Dept;
import com.j2ee.entity.Dept4Boss;
import com.j2ee.mapper.DeptMapper;
import com.j2ee.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-19
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Autowired
    DeptMapper deptMapper;
    @Override
    public List<Dept4Boss> SearchDept(String kw){
        return deptMapper.SearchDept('%'+kw+'%');
    }
}
