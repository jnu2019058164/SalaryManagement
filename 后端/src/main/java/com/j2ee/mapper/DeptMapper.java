package com.j2ee.mapper;

import com.j2ee.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.j2ee.entity.Dept4Boss;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-19
 */
public interface DeptMapper extends BaseMapper<Dept> {
    List<Dept4Boss> SearchDept(String kw);
}
