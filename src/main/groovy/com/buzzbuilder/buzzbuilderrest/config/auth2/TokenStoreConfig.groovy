package com.buzzbuilder.buzzbuilderrest.config.auth2

import com.buzzbuilder.buzzbuilderrest.support.BootSecurityProperties
import com.buzzbuilder.buzzbuilderrest.support.common.TokenStoreType
import org.springframework.beans.factory.BeanCreationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore

import javax.sql.DataSource

@Configuration
public class TokenStoreConfig {

    private BootSecurityProperties properties;

    private RedisConnectionFactory factory;


    private DataSource dataSource;

    @Autowired(required = false)
    public TokenStoreConfig(BootSecurityProperties properties, RedisConnectionFactory factory, DataSource dataSource) {
        this.properties = properties;
        this.factory = factory;

        this.dataSource = dataSource;
    }

    @Bean
    public TokenStore tokenStore() throws Exception {

        TokenStore store = null;

        switch (properties.getTokenStoreType()) {
            case TokenStoreType.jwt:
                store = new JwtTokenStore(jwtAccessTokenConverter());
                break;
            case TokenStoreType.redis:
                if (factory == null) {
                    throw new BeanCreationException("Configuring Redis to store Token requires redisConnectionFactory bean, not found");
                }
                store = new RedisTokenStore(factory);
                break;
            case TokenStoreType.jdbc:

                if (dataSource == null) {
                    throw new BeanCreationException("Configuring jdbc to store Token requires dataSource bean, not found");
                }
                store = new JdbcTokenStore(dataSource);
                break;
            default:
                store = new InMemoryTokenStore();
        }

        return store;
    }

    @Bean
    @Primary
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        converter.setSigningKey(properties.getTokenSigningKey());

        return converter;
    }
}