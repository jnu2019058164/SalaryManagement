package com.j2ee.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("STF")
public class Stf implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "STFID", type = IdType.AUTO)
    private BigDecimal stfid;

    @TableField("STFNAME")
    private String stfname;

    @TableField("POSID")
    private BigDecimal posid;

    @TableField("ENTRYDATE")
    private Date entrydate;


}
