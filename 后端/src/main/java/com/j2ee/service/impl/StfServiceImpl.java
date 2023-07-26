package com.j2ee.service.impl;

import com.j2ee.entity.Stf;
import com.j2ee.mapper.StfMapper;
import com.j2ee.service.StfService;
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
public class StfServiceImpl extends ServiceImpl<StfMapper, Stf> implements StfService {
    @Autowired
    StfMapper StfMap;
    @Override
    public List<Stf> SearchStf(String kw){
        return StfMap.SearchStf('%'+kw+'%');
    }
}
