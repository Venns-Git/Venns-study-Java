package com.venns.springcloud.service;

import com.venns.springcloud.pojo.Dept;

import java.util.List;

/**
 * @author Venns
 * @CreateDate 2021/2/4
 */
public interface DeptService {

    public boolean addDept(Dept dept);

    public Dept queryById(long id);

    public List<Dept> queryAll();
}
