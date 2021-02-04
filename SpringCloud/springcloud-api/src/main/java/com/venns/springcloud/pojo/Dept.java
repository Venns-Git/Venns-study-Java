package com.venns.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Venns
 * @CreateDate 2021/2/4
 */
@Data
@NoArgsConstructor
@Accessors(chain = true) //链式编程
public class Dept implements Serializable {
    private Long deptno;
    private String dname;
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }


}
