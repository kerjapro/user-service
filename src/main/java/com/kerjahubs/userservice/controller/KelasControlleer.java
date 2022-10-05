package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.RequestAddProduct;
import com.kerjahubs.userservice.service.AddEditKelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KelasControlleer {
    @Autowired
    AddEditKelasService addEditKelasService;

    @PostMapping(value = UrlValues.kelas)
    public ResponseEntity<?> registerRetail(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestAddProduct requestAddProduct
    ){
        return new ResponseEntity<>(
            addEditKelasService.addEditKelas(
                new BaseRequest<>(
                    requestAddProduct
                )
            ),
            HttpStatus.OK
        );
    }
}
