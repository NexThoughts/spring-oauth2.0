package com.buzzbuilder.buzzbuilderrest.config.auth2

import com.buzzbuilder.buzzbuilderrest.filter.BootBasicAuthenticationFilter
import com.buzzbuilder.buzzbuilderrest.support.oauth2.BootClientDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore
import org.springframework.security.web.AuthenticationEntryPoint

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BootClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter converter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;


    @Autowired
    private WebResponseExceptionTranslator bootWebResponseExceptionTranslator;

    @Autowired
    private BootBasicAuthenticationFilter filter;


    public OAuth2AuthorizationServerConfig() {
        super();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {


        security.allowFormAuthenticationForClients();

        filter.setClientDetailsService(clientDetailsService);

        security.authenticationEntryPoint(authenticationEntryPoint);

        security.addTokenEndpointAuthenticationFilter(filter);

        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET);

        if (!(tokenStore instanceof RedisTokenStore) && this.converter != null) {
            endpoints.accessTokenConverter(converter);
        }

//         Handle the exception thrown by ExceptionTranslationFilter
        endpoints.exceptionTranslator(bootWebResponseExceptionTranslator);

        endpoints.pathMapping("/oauth/confirm_access", "/custom/confirm_access");
    }

}
