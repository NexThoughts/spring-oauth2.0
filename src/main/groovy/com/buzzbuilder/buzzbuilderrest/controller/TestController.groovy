package com.buzzbuilder.buzzbuilderrest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("/other")
    public String test(){
        return "other";
    }

    @GetMapping("/home")
    @SuppressWarnings("unchecked")
    public ResponseEntity<String> howdy(Model model, Principal principal) {
        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        Map<String, Object> user = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        model.addAttribute("user", user);
        println "user::::::::"+user
        return new ResponseEntity<String>("hello", HttpStatus.ACCEPTED)

    }

}
