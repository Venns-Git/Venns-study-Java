package com.venns.mybatis_plus.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: Venns
 * @create: 2022/6/17 11:51
 **/
@EnableTransactionManagement
@MapperScan("com.venns.mybatis_plus.mapper")
@Configuration
public class MyBatisPlusConfig {



}
