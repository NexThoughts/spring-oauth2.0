package com.buzzbuilder.buzzbuilderrest.service.impl;

import com.buzzbuilder.buzzbuilderrest.entity.Client;
import com.buzzbuilder.buzzbuilderrest.mapper.ClientMapper;
import com.buzzbuilder.buzzbuilderrest.service.IClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.buzzbuilder.buzzbuilderrest.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuit
 * @create time 2018/10/16  10:06
 * @description
 * @modify by
 * @modify time
 **/
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper,Client> implements IClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Client findClientByClientId(String clientId) {
        return this.clientMapper.findClientByClientId(clientId);
    }
}
