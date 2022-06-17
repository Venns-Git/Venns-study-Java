package com.venns.mybatis_plus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: Venns
 * @create: 2022/6/17 10:45
 **/
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    /*
        插入时的填充策略
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("==========start insert fill==========");
        /**
         * @filedName 需要处理的字段
         * @fileVal 填充的值
         * @metaObject 元数据
         */
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("========start update fill=============");
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
