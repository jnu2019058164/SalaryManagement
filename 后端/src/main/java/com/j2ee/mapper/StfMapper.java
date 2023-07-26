package com.j2ee.mapper;

import com.j2ee.entity.Stf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-19
 */
public interface StfMapper extends BaseMapper<Stf> {
    public List<Stf> SearchStf(String kw);
}
