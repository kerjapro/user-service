package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.kelas.RequestAddBenefitProduct;
import com.kerjahubs.userservice.model.request.kelas.RequestAddModuleProduct;
import com.kerjahubs.userservice.model.request.kelas.RequestAddProduct;
import com.kerjahubs.userservice.service.kelas.ManageBenefitService;
import com.kerjahubs.userservice.service.kelas.ManageKelasService;
import com.kerjahubs.userservice.service.kelas.ManageModulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KelasController {
    @Autowired
    ManageKelasService addEditKelasService;
    @Autowired
    ManageBenefitService manageBenefitService;
    @Autowired
    ManageModulService manageModulService;

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
            manageBenefitService.addEditBenefitKelas(
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
            manageModulService.addEditModulKelas(
                new BaseRequest<>(
                    requestAddModuleProduct
                )
            ),
            HttpStatus.OK
        );
    }
}
