package com.j2ee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dept4Boss {
    private BigDecimal deptid;
    private String deptname;
    private List<Pos> deptPos;

    public void setDeptPos(List<Pos> deptPos) {
        this.deptPos = deptPos;
    }
    public List<Pos> getDeptPos() {
        return deptPos;
    }

}
