package com.xm.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author 尹晓蒙
 * @date 2020-05-21 15:23
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.xm.mapper"})
@PropertySource("classpath:application.properties")
public class DataConfig {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource createDateSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(environment.getProperty("mysql.jdbc.url"));
        dataSource.setUsername(environment.getProperty("mysql.jdbc.userName"));
        dataSource.setPassword(environment.getProperty("mysql.jdbc.passWord"));
        dataSource.setDriverClassName(environment.getProperty("mysql.jdbc.driver"));
        //最小空闲连接
        dataSource.setMinimumIdle(10);
        //最大连接数，小于等于0会被重置为默认值10；大于零小于1会被重置为minimum-idle的值
        dataSource.setMaximumPoolSize(20);
        //空闲连接超时时间，默认值600000（10分钟），大于等于max-lifetime且max-lifetime>0，会被重置为0；不等于0且小于10秒，会被重置为10秒
        dataSource.setIdleTimeout(500000);
        //连接最大存活时间，不等于0且小于30秒，会被重置为默认值30分钟.设置应该比mysql设置的超时时间短
        dataSource.setMaxLifetime(540000);
        //连接超时时间：毫秒，小于250毫秒，否则被重置为默认值30秒
        dataSource.setConnectionTimeout(60000);
        return dataSource;
    }
    // 2.MybatisSqlSessionFactoryBean工厂
    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(DataSource dataSource,PaginationInterceptor paginationInterceptor) throws IOException { // 使用依赖注入的形式注入过来
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        // 传入数据源
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.mpdidb.domain");
        factory.setMapperLocations(patternResolver.getResources("classpath*:mapper/**/*.xml"));
        // 分页插件
        factory.setPlugins(new PaginationInterceptor[]{paginationInterceptor});
        return factory;
    }
    //mybatis-plus分页
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
    //4. 事务管理， 开启事务使用类注解
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){ // 数据源依赖注入
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
