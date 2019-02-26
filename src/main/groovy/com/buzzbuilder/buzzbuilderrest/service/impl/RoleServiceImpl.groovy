package com.buzzbuilder.buzzbuilderrest.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.buzzbuilder.buzzbuilderrest.entity.Role
import com.buzzbuilder.buzzbuilderrest.mapper.RoleMapper
import com.buzzbuilder.buzzbuilderrest.service.IRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired(required = true)
    private RoleMapper roleMapper;


    @Override
    public List<Role> findByUserId(String userId) {
        return roleMapper.findByUserId(userId);
    }


}
