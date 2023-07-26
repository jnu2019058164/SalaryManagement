package com.j2ee.service.impl;

import com.j2ee.entity.Sal;
import com.j2ee.mapper.SalMapper;
import com.j2ee.service.SalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author CV
 * @since 2022-11-19
 */
@Service
public class SalServiceImpl extends ServiceImpl<SalMapper, Sal> implements SalService {

}
