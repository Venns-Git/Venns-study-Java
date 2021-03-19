package com.venns.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author: Venns
 * @create: 2021/3/19 16:34
 **/
@Configurable
public class MyRuleConfig {
    @Bean
    public IRule myRule(){
        return new MyRule();
    }
}
