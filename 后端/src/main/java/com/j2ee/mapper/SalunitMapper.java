package com.j2ee.mapper;

import com.j2ee.entity.Dept4Boss;
import com.j2ee.entity.Salunit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CV
 * @since 2022-11-19
 */
public interface SalunitMapper extends BaseMapper<Salunit> {
    List<Salunit> SearchSUnit(String kw);
}
