package com.xxy.config;

import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xxy.dao")   // 搜索接口的代理对象
public class MybatisPlusConfig {

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
