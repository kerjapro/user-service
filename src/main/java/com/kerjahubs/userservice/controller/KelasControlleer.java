package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.RequestAddBenefitProduct;
import com.kerjahubs.userservice.model.request.RequestAddModuleProduct;
import com.kerjahubs.userservice.model.request.RequestAddProduct;
import com.kerjahubs.userservice.service.AddEditBenefitService;
import com.kerjahubs.userservice.service.AddEditKelasService;
import com.kerjahubs.userservice.service.AddEditModulService;
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
    @Autowired
    AddEditBenefitService addEditBenefitService;
    @Autowired
    AddEditModulService addEditModulService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.kelas)
    public ResponseEntity<?> kelas(
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

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.benefit)
    public ResponseEntity<?> benefit(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestAddBenefitProduct requestAddBenefitProduct
    ){
        return new ResponseEntity<>(
            addEditBenefitService.addEditBenefitKelas(
                new BaseRequest<>(
                    requestAddBenefitProduct
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.modul)
    public ResponseEntity<?> modul(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestAddModuleProduct requestAddModuleProduct
    ){
        return new ResponseEntity<>(
            addEditModulService.addEditModulKelas(
                new BaseRequest<>(
                    requestAddModuleProduct
                )
            ),
            HttpStatus.OK
        );
    }
}
