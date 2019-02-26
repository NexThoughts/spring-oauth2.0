package com.buzzbuilder.buzzbuilderrest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.buzzbuilder.buzzbuilderrest.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user u join user_role ur on u.id=ur.user_id join role r on r.id=ur.role_id where u.enabled=1 and u.username=#{u1}")
    User findByUserName(@Param("u1") String username);

}
