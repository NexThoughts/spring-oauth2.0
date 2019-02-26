package com.buzzbuilder.buzzbuilderrest.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DbConfig {

    private String username;
    private String url;
    private String  password;
    private String driverClassName;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
         * <!-- Configure initialization size, minimum, maximum -->
             <property name="initialSize" value="10" />
             <property name="minIdle" value="30" />
             <property name="maxActive" value="300" />
             <!-- Configure the time to get the connection waiting timeout -->
             <property name="maxWait" value="3600000" />
             <!-- How long does it take to configure the interval to detect the idle connection that needs to be closed, in milliseconds -->
             <property name="timeBetweenEvictionRunsMillis" value="60000" />
             <!-- Configure the minimum lifetime of a connection in the pool, in milliseconds -->
             <property name="minEvictableIdleTimeMillis" value="30000" />
             <property name="validationQuery" value="SELECT 'x' FROM dual" />
             <property name="testWhileIdle" value="true" />
             <property name="testOnBorrow" value="false" />
             <property name="testOnReturn" value="false" />
             <!-- Open PSCache and specify the size of PSCache on each connection -->
             <property name="poolPreparedStatements" value="true" />
             <property name="maxPoolPreparedStatementPerConnectionSize"
             Value="20" />
         *
         * */
    @Bean
    @Primary
    public DataSource dataSource() {
        DruidDataSource dr = new DruidDataSource();
        dr.setUsername(this.username);
        dr.setPassword(this.password);
        dr.setDriverClassName(this.driverClassName);
        dr.setUrl(this.url);

        this.logger.info(this.url+":"+this.username+":"+this.password);

        dr.setInitialSize(10);
        dr.setMinIdle(30);
        dr.setMaxActive(300);
        dr.setMaxWait(3600000);
        dr.setTimeBetweenEvictionRunsMillis(60000);
        dr.setMinEvictableIdleTimeMillis(30000);
        dr.setValidationQuery("select 'X' from dual");
        dr.setTestWhileIdle(true);
        dr.setTestOnBorrow(false);
        dr.setTestOnReturn(false);
        dr.setPoolPreparedStatements(true);
        dr.setMaxPoolPreparedStatementPerConnectionSize(20);

        return dr;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

}
