package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.kelas.RequestAddBenefitProduct;
import com.kerjahubs.userservice.model.request.kelas.RequestAddModuleProduct;
import com.kerjahubs.userservice.model.request.kelas.RequestAddProduct;
import com.kerjahubs.userservice.service.kelas.AddEditBenefitService;
import com.kerjahubs.userservice.service.kelas.AddEditKelasService;
import com.kerjahubs.userservice.service.kelas.AddEditModulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KelasController {
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
