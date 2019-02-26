package com.buzzbuilder.buzzbuilderrest.support

import com.buzzbuilder.buzzbuilderrest.entity.Role
import com.buzzbuilder.buzzbuilderrest.entity.User
import com.buzzbuilder.buzzbuilderrest.service.IRoleService
import com.buzzbuilder.buzzbuilderrest.service.IUserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
public final class BootUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userService.findByUserName(username);
        if (user) {
            List<Role> roles = this.roleService.findByUserId(user.id)
            List<GrantedAuthority> authorities = []

            println roles.authority
            roles.each {
                GrantedAuthority authority = new SimpleGrantedAuthority(it.authority);
                authorities.add(authority)
            }
            user.setAuthorities(authorities)
            println user.properties
        }


        if (!user) {
            throw new UsernameNotFoundException("Username does not exist");
        }

        return user;
    }
}
