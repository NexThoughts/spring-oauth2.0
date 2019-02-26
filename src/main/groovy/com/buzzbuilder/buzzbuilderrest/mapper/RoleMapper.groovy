package com.buzzbuilder.buzzbuilderrest.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.buzzbuilder.buzzbuilderrest.entity.Role
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Repository
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from user_role ur join role r on r.id=ur.role_id where  ur.user_id=#{u1}")
    List<Role> findByUserId(@Param("u1") String userId);
}
