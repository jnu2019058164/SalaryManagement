package com.j2ee.service;

import com.j2ee.entity.Stf;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-19
 */
public interface StfService extends IService<Stf> {
    public List<Stf> SearchStf(String kw);
}
