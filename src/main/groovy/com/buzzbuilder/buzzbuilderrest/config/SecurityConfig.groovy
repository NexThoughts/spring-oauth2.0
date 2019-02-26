package com.buzzbuilder.buzzbuilderrest.config

import com.buzzbuilder.buzzbuilderrest.support.BootLoginFailureHandler
import com.buzzbuilder.buzzbuilderrest.support.BootSecurityProperties
import com.buzzbuilder.buzzbuilderrest.support.BootUserDetailService
import com.buzzbuilder.buzzbuilderrest.support.oauth2.BootOAuth2AuthExceptionEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.ClientDetailsService

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BootUserDetailService userDetailService;

    @Autowired
    private BootSecurityProperties properties;

    @Autowired
    private BootLoginFailureHandler handler;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    BootOAuth2AuthExceptionEntryPoint authenticationEntryPoint;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html/**", "/webjars/**",
                "/swagger-resources/**", "/v2/api-docs/**",
                "/swagger-resources/configuration/ui/**", "/swagger-resources/configuration/security/**",
                "/images/**", "/css/**", "/static/css/**")
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
         // Must be configured, otherwise the http configuration of OAuth2 will not take effect----not aware
                .requestMatchers()
                .antMatchers("/auth/login", properties.getLoginProcessUrl(), "/oauth/authorize")
                .and()
                .authorizeRequests()
        // Customize the page or handle the url. If the global permission is not configured, the browser will prompt the server to forward the page multiple times.
                .antMatchers("/auth/login", properties.getLoginProcessUrl(),
                "/js/**",
                "/css/**",
                "/img/**",
                "/font-awesome/**",
                "/ico/**",
                "/webjars/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        // form login
        http.formLogin()
        // failureHandler
//               .failureHandler(handler)
//        page
                .loginPage("/auth/login")
        // login processing url
                .loginProcessingUrl(properties.getLoginProcessUrl());

        http.httpBasic().disable();


    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
