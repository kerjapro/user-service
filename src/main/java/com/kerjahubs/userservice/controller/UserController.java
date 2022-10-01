package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.RequestRegister;
import com.kerjahubs.userservice.service.RegisterUserRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    RegisterUserRetailService registerUserRetailService;

    @GetMapping(value = UrlValues.registerRetail)
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

}
