package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.RequestLogin;
import com.kerjahubs.userservice.model.request.RequestRegister;
import com.kerjahubs.userservice.model.request.RequestRegisterPartner;
import com.kerjahubs.userservice.service.LoginService;
import com.kerjahubs.userservice.service.RegisterPartnerService;
import com.kerjahubs.userservice.service.RegisterUserRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    LoginService loginService;

    @Autowired
    RegisterUserRetailService registerUserRetailService;

    @Autowired
    RegisterPartnerService registerPartnerService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.login)
    public ResponseEntity<?> login(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestLogin requestLogin
    ){
        return new ResponseEntity<>(
            loginService.login(
                new BaseRequest<>(
                    requestLogin
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.registerRetail)
    public ResponseEntity<?> registerRetail(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestRegister requestRegister
        ){
        return new ResponseEntity<>(
            registerUserRetailService.registerUserRetail(
                new BaseRequest<>(
                    requestRegister
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.registerPartner)
    public ResponseEntity<?> registerPartner(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestRegisterPartner requestRegisterPartner
    ){
        return new ResponseEntity<>(
            registerPartnerService.registerPartner(
                new BaseRequest<>(
                    requestRegisterPartner
                )
            ),
            HttpStatus.OK
        );
    }
}
