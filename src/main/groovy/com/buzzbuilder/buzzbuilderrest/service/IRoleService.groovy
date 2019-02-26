package com.buzzbuilder.buzzbuilderrest.service

import com.baomidou.mybatisplus.extension.service.IService
import com.buzzbuilder.buzzbuilderrest.entity.Role

public interface IRoleService extends IService<Role> {

    List<Role> findByUserId(String userId);

}
