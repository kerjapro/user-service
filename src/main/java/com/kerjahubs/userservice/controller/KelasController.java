package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.kelas.RequestAddBenefitProduct;
import com.kerjahubs.userservice.model.request.kelas.RequestAddModuleProduct;
import com.kerjahubs.userservice.model.request.kelas.RequestAddProduct;
import com.kerjahubs.userservice.model.request.kelas.RequestManageDiscountProduct;
import com.kerjahubs.userservice.service.kelas.ManageKelasBenefitService;
import com.kerjahubs.userservice.service.kelas.ManageKelasDiscountService;
import com.kerjahubs.userservice.service.kelas.ManageKelasService;
import com.kerjahubs.userservice.service.kelas.ManageKelasModulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KelasController {
    @Autowired
    ManageKelasService addEditKelasService;
    @Autowired
    ManageKelasBenefitService manageKelasBenefitService;
    @Autowired
    ManageKelasModulService manageKelasModulService;

    @Autowired
    ManageKelasDiscountService manageKelasDiscountService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.kelas)
    public ResponseEntity<?> kelas(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestAddProduct requestAddProduct
    ) {
        return new ResponseEntity<>(
            addEditKelasService.manageKelas(
                new BaseRequest<>(
                    requestAddProduct
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.benefitKelas)
    public ResponseEntity<?> benefit(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestAddBenefitProduct requestAddBenefitProduct
    ) {
        return new ResponseEntity<>(
            manageKelasBenefitService.manageKelasBenefit(
                new BaseRequest<>(
                    requestAddBenefitProduct
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.modulKelas)
    public ResponseEntity<?> modul(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestAddModuleProduct requestAddModuleProduct
    ) {
        return new ResponseEntity<>(
            manageKelasModulService.manageKelasModul(
                new BaseRequest<>(
                    requestAddModuleProduct
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.discountKelas)
    public ResponseEntity<?> discount(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestManageDiscountProduct requestManageDiscountProduct
    ) {
        return new ResponseEntity<>(
            manageKelasDiscountService.manageKelasDiscount(
                new BaseRequest<>(
                    requestManageDiscountProduct
                )
            ),
            HttpStatus.OK
        );
    }
}
