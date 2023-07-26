package com.j2ee.service.impl;

import com.j2ee.entity.Salunit;
import com.j2ee.mapper.SalunitMapper;
import com.j2ee.service.SalunitService;
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
public class SalunitServiceImpl extends ServiceImpl<SalunitMapper, Salunit> implements SalunitService {
    @Autowired
    SalunitMapper SMap;
    public List<Salunit> SearchSUnit(String kw){
        return SMap.SearchSUnit('%'+kw+'%');
    }
}
