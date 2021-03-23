package com.venns.springcloud.service;

import com.venns.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DeptClientServiceFallbcakFactory.class)
public interface DeptClientService {

    @RequestMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") long id);

    @RequestMapping("/dept/list")
    public List<Dept> queryAll();

    @RequestMapping("/dept/list")
    public boolean addDept(Dept dept);
}
