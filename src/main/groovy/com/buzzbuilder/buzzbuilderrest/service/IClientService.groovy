package com.buzzbuilder.buzzbuilderrest.service;

import com.buzzbuilder.buzzbuilderrest.entity.Client;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;



public interface IClientService extends IService<Client> {
    Client findClientByClientId(@Param("id") String clientId);
}
