package com.venns.springcloud.service;

import com.venns.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Venns
 * @create: 2021/3/23 19:22
 **/
//降级
@Component
public class DeptClientServiceFallbcakFactory implements FallbackFactory {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept queryById(long id) {
                return new Dept().setDeptno(id).setDname("id == > "+id + "，没有对应的信息，客户端提供了降级的服务，这个服务已经被关闭").setDb_source("没有数据");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
