package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.kerja.RequestKerja;
import com.kerjahubs.userservice.model.request.kerja.RequestKerjaBenefit;
import com.kerjahubs.userservice.model.request.kerja.RequestKerjaRequirement;
import com.kerjahubs.userservice.service.kerja.ManageKerjaBenefitService;
import com.kerjahubs.userservice.service.kerja.ManageKerjaService;
import com.kerjahubs.userservice.service.kerja.ManageKerjaRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KerjaController {
    @Autowired
    ManageKerjaService manageKerjaService;

    @Autowired
    ManageKerjaBenefitService manageKerjaBenefitService;

    @Autowired
    ManageKerjaRequirementService manageKerjaRequirementService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.kerja)
    public ResponseEntity<?> kelas(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestKerja requestKerja
    ) {
        return new ResponseEntity<>(
            manageKerjaService.manageKerja(
                new BaseRequest<>(
                    requestKerja
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.benefitKerja)
    public ResponseEntity<?> benefitKerja(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestKerjaBenefit requestKerjaBenefit
    ) {
        return new ResponseEntity<>(
            manageKerjaBenefitService.manageKerjaBenefit(
                new BaseRequest<>(
                    requestKerjaBenefit
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.requirementKerja)
    public ResponseEntity<?> requirementKerja(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestKerjaRequirement requestKerjaRequirement
    ) {
        return new ResponseEntity<>(
            manageKerjaRequirementService.manageKerjaRequirement(
                new BaseRequest<>(
                    requestKerjaRequirement
                )
            ),
            HttpStatus.OK
        );
    }
}
