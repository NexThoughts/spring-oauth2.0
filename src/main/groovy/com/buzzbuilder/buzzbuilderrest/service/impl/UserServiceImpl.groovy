package com.buzzbuilder.buzzbuilderrest.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.buzzbuilder.buzzbuilderrest.entity.User
import com.buzzbuilder.buzzbuilderrest.mapper.UserMapper
import com.buzzbuilder.buzzbuilderrest.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired(required = true)
    private UserMapper userMapper;


    @Override
    public List<User> getUsers() {

        User dd = userMapper.findByUserName("yuit");

        return userMapper.selectList(null);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }


}
