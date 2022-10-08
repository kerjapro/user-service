package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.RequestParameters;
import com.kerjahubs.userservice.service.BannersService;
import com.kerjahubs.userservice.service.ParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParamController {

    @Autowired
    BannersService bannersService;

    @Autowired
    ParametersService parametersService;

    @CrossOrigin(origins = "*")
    @GetMapping(value = UrlValues.getBanners)
    public ResponseEntity<?> getBanners(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel
    ) {
        return new ResponseEntity<>(
            bannersService.getBanners(
                new BaseRequest<>(DefaultValues.emptyString)
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.getParameters)
    public ResponseEntity<?> getParameters(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestParameters requestParameters
    ) {
        return new ResponseEntity<>(
            parametersService.getParameters(
                new BaseRequest<>(
                    requestParameters
                )
            ),
            HttpStatus.OK
        );
    }
}
