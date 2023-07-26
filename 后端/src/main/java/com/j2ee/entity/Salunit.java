package com.j2ee.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("SALUNIT")
public class Salunit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("SALID")
    private BigDecimal salid;

    private Sal sal;

    @TableField("STFID")
    private BigDecimal stfid;

    private String stfname;

    @TableField("SALWT")
    private Float salwt;


}
