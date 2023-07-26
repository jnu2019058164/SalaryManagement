package com.j2ee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.j2ee.entity.Salary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryMapper  extends BaseMapper<Salary> {
    List<Salary> test(int i);
}
