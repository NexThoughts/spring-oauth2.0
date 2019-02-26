package com.buzzbuilder.buzzbuilderrest.support.oauth2;

import com.buzzbuilder.buzzbuilderrest.entity.Client;
import com.buzzbuilder.buzzbuilderrest.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;


@Component
public final class BootClientDetailsService implements ClientDetailsService {

    @Autowired
    private IClientService clientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        Client client = this.clientService.findClientByClientId(clientId);

        if(client==null){
            throw new ClientRegistrationException("No connection");
        }
        BootClientDetails details=new BootClientDetails(client);

        return details;
    }

}
