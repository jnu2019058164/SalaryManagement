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
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SAL")
public class Sal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "SALID", type = IdType.ASSIGN_ID)
    private BigDecimal salid;

    @TableField("SALNAME")
    private String salname;

    @TableField("SALUNIT")
    private String salunit;

    @TableField("UNITVAL")
    private Float unitval;

    @TableField("POSID")
    private BigDecimal posid;


}
