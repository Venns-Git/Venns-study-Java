package com.venns.mybatis_plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author: Venns
 * @create: 2022/6/20 16:41
 **/
public class AutoGeneratorTest {
    public static void main(String[] args) {
        // 1. 构建一个代码生成器对象
        AutoGenerator ag = new AutoGenerator();

        // 2. 配置策略
        // 2.1 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); //获取当前项目路径
        gc.setOutputDir(projectPath+"/src/main/java"); // 将代码生成到项目路径下
        gc.setAuthor("Venns"); // 设置作者
        gc.setOpen(false); // 生成完是否打开文件夹
        gc.setFileOverride(true); // 是否覆盖文件
        gc.setServiceName("%sService"); // service文件名字格式
        gc.setIdType(IdType.ID_WORKER); // 主键生成策略
        gc.setDateType(DateType.ONLY_DATE); // 日期格式
        gc.setSwagger2(true); // 是否配置swagger
        ag.setGlobalConfig(gc);
        // 2.2 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL); // 数据库类型
        dsc.setDriverName("com.mysql.cj.jdbc.Driver"); // 驱动名字
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai"); // url
        dsc.setUsername("root");
        dsc.setPassword("123456");
        ag.setDataSource(dsc);
        // 2.3 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("generator"); // 模块名
        pc.setParent("com.venns"); // 包路径
        pc.setEntity("entity"); // 实体类包命名
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        ag.setPackageInfo(pc);
        // 2.4 策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("user"); // 设置要映射的表名
        sc.setNaming(NamingStrategy.underline_to_camel); // 设置类命名规则-下划线转驼峰
        sc.setColumnNaming(NamingStrategy.underline_to_camel); //设置字段命名规则
        sc.setEntityLombokModel(true); //是否使用Lombok
        sc.setRestControllerStyle(true); // controller采用restful风格
        //同时,也可以配置自动填充,逻辑删除,乐观锁等等...
        ag.setStrategy(sc);

        // 3. 执行代码生成器
        ag.execute();
    }
}
