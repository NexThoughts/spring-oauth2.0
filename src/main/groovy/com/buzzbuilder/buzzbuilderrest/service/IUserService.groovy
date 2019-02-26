package com.buzzbuilder.buzzbuilderrest.service;

import com.buzzbuilder.buzzbuilderrest.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IUserService  extends IService<User> {


    public List<User> getUsers();


    User findByUserName(String userName);

}
