package com.j2ee.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CV
 * @since 2022-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DEPT")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "DEPTID", type = IdType.ASSIGN_ID)
    private BigDecimal deptid;

    @TableField("DEPTNAME")
    private String deptname;


}
