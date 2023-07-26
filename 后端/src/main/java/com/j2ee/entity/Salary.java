package com.j2ee.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Salary {
    private String deptname;
    private String posname;
    private String stfname;
    private float salary;
}
