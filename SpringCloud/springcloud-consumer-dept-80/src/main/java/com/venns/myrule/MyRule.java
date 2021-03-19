package com.venns.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: Venns
 * @create: 2021/3/19 16:23
 **/
public class MyRule extends AbstractLoadBalancerRule {
    private int total = 0; //共访问服务次数
    private int currentIndex = 0; //当前服务的下标
    public MyRule() {
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers(); //获得所有可用的服务
                List<Server> allList = lb.getAllServers(); //获得所有的服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                if (total < 5){
                    server =  upList.get(currentIndex);
                    total++;
                }else {
                    total = 0;
                    currentIndex++;
                    if (currentIndex >= upList.size()){
                        currentIndex = 0;
                    }
                    server = upList.get(currentIndex);
                }

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
