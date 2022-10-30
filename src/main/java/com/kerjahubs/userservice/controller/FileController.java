package com.kerjahubs.userservice.controller;

import com.kerjahubs.common.constant.RequestHeaders;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.userservice.constant.UrlValues;
import com.kerjahubs.userservice.model.request.file.RequestUploadFile;
import com.kerjahubs.userservice.model.request.user.RequestEditProfileDataPartner;
import com.kerjahubs.userservice.service.file.FileStorageService;
import com.kerjahubs.userservice.service.file.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {
    @Autowired
    private UploadFileService uploadFileService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = UrlValues.uploadFile)
    public ResponseEntity<?> uploadFile(
        @RequestHeader(RequestHeaders.language) String language,
        @RequestHeader(RequestHeaders.channel) String channel,
        @RequestParam("file") MultipartFile file,
        @RequestParam("fileType") String fileType,
        @RequestParam("groupType") String groupType
    ) {
        RequestUploadFile request = new RequestUploadFile();
        request.setFile(file);
        request.setFileType(fileType);
        request.setGroupType(groupType);

        return new ResponseEntity<>(
            uploadFileService.upload(
                new BaseRequest<>(
                    request
                )
            ),
            HttpStatus.OK
        );
    }
}
