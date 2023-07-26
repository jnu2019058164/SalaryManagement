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
@TableName("ACCT")
public class Acct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ACCTID", type = IdType.ASSIGN_ID)
    private BigDecimal acctid;

    @TableField("ACCTNO")
    private String acctno;

    @TableField("ACCTPWD")
    private String acctpwd;

    @TableField("AUTHLV")
    private BigDecimal authlv;


}
