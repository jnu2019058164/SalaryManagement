package com.j2ee.service.impl;

import com.j2ee.entity.Acct;
import com.j2ee.mapper.AcctMapper;
import com.j2ee.service.AcctService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-19
 */
@Service
public class AcctServiceImpl extends ServiceImpl<AcctMapper, Acct> implements AcctService {

}
