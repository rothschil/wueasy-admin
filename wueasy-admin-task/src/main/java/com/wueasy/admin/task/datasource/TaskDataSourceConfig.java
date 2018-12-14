/*
 * wueasy - A Java Distributed Rapid Development Platform.
 * Copyright (C) 2017-2019 wueasy.com

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.wueasy.admin.task.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * 默认数据源配置
 * @author: fallsea
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
public class TaskDataSourceConfig {

	@Bean(name = "taskDataSource")
    @ConfigurationProperties(prefix = "wueasy.datasource.task")
    @Primary
    public DruidDataSource taskDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
    

    @Bean(name = "taskSqlSessionFactory")
    public SqlSessionFactory taskSqlSessionFactory(@Qualifier("taskDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/wueasy/task/**/*.xml"));
        // 加载全局的配置文件
        sessionFactory.setConfigLocation(
                new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name = "taskTransactionManager")
    public DataSourceTransactionManager taskTransactionManager(@Qualifier("taskDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "taskSqlSessionTemplate")
    public SqlSessionTemplate taskSqlSessionTemplate(@Qualifier("taskSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean
    public MapperScannerConfigurer taskMapperScannerConfigurer()
    {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.wueasy.admin.task.mapper");
        configurer.setSqlSessionTemplateBeanName("taskSqlSessionTemplate");
        return configurer;
    }
}
