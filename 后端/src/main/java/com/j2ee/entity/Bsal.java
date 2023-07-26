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
@TableName("BSAL")
public class Bsal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("BASESAL")
    private Float basesal;

    @TableField("POSID")
    private BigDecimal posid;


}
