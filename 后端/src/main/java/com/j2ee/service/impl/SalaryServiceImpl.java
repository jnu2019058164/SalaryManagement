package com.j2ee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.j2ee.entity.Salary;
import com.j2ee.mapper.SalaryMapper;
import com.j2ee.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements SalaryService {
    @Autowired
    SalaryMapper Smap;
    @Override
    public List<Salary> test(int i){
        return Smap.test(2);
    }
}
