package com.buzzbuilder.buzzbuilderrest.controller;

import com.buzzbuilder.buzzbuilderrest.entity.Client;
import com.buzzbuilder.buzzbuilderrest.response.BaseResponse;
import com.buzzbuilder.buzzbuilderrest.response.HttpResponse;
import com.buzzbuilder.buzzbuilderrest.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public BaseResponse clientRegistered(@RequestBody @Valid Client client) {

        client.setClientSecret(passwordEncoder.encode(client.getClientSecret()));
        boolean i = clientService.save(client);
        return HttpResponse.baseResponse(200);
    }

}
