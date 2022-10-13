package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.user.*;
import com.kerjahubs.userservice.service.user.LoginService;
import com.kerjahubs.userservice.service.user.*;
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

    @Autowired
    InquiryProfilePartnerService inquiryProfilePartnerService;

    @Autowired
    EditProfileRetailService editProfileRetailService;

    @Autowired
    EditProfilePartnerService editProfilePartnerService;

    @Autowired
    ChangePasswordService changePasswordService;

    @Autowired
    AddProfileDocumentService addProfileDocumentService;

    @Autowired
    EditProfileDocumentService editProfileDocumentService;

    @Autowired
    AddProfileLinkedAccountService addProfileLinkedAccountService;

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

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.editProfileRetail)
    public ResponseEntity<?> editProfile(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestEditProfileDataRetail requestEditProfileDataRetail
    ){
        return new ResponseEntity<>(
            editProfileRetailService.editProfile(
                new BaseRequest<>(
                    requestEditProfileDataRetail
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.editProfilePartner)
    public ResponseEntity<?> editProfilePartner(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestEditProfileDataPartner requestEditProfileDataPartner
    ){
        return new ResponseEntity<>(
            editProfilePartnerService.editProfilePartner(
                new BaseRequest<>(
                    requestEditProfileDataPartner
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.inquiryProfilePartner)
    public ResponseEntity<?> getProfilePartner(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestInquiryProfilePartner requestInquiryProfilePartner
    ){
        return new ResponseEntity<>(
            inquiryProfilePartnerService.getProfilePartner(
                new BaseRequest<>(
                    requestInquiryProfilePartner
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.changePassword)
    public ResponseEntity<?> changePassword(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestChangePassword requestChangePassword
    ){
        return new ResponseEntity<>(
            changePasswordService.changePassword(
                new BaseRequest<>(
                    requestChangePassword
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.addDocument)
    public ResponseEntity<?> addDocument(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestAddProfileDokumen requestAddProfileDokumen
    ){
        return new ResponseEntity<>(
            addProfileDocumentService.addDocument(
                new BaseRequest<>(
                    requestAddProfileDokumen
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.editDocument)
    public ResponseEntity<?> editDocument(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestEditProfileDocument requestEditProfileDocument
    ){
        return new ResponseEntity<>(
            editProfileDocumentService.editDocument(
                new BaseRequest<>(
                    requestEditProfileDocument
                )
            ),
            HttpStatus.OK
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.addLinkedAccount)
    public ResponseEntity<?> addDocument(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestBody RequestAddProfileLinkedAccount requestAddProfileLinkedAccount
    ){
        return new ResponseEntity<>(
            addProfileLinkedAccountService.addLinkedAccount(
                new BaseRequest<>(
                    requestAddProfileLinkedAccount
                )
            ),
            HttpStatus.OK
        );
    }
}
