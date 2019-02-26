package com.buzzbuilder.buzzbuilderrest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.buzzbuilder.buzzbuilderrest.entity.Client;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientMapper extends BaseMapper<Client> {


    @Select("select * from clients where clientId=#{id}")
    Client findClientByClientId(@Param("id") String clientId);

}
