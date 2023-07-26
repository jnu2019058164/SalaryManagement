package com.j2ee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.j2ee.entity.Salary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SalaryService  extends IService<Salary> {
    List<Salary> test(int i);
}
