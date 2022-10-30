package com.kerjahubs.userservice.service.file;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.constant.ParameterValues;
import com.kerjahubs.userservice.model.request.file.RequestUploadFile;
import com.kerjahubs.userservice.model.response.ResponseUploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileService {
    @Autowired
    public FileStorageService fileStorageService;

    public BaseResponse<ResponseUploadFile> upload(BaseRequest<RequestUploadFile> baseRequest) {
        BaseResponse<ResponseUploadFile> response = new BaseResponse<>();

        try {
            String fileName = fileStorageService.storeFile(
                baseRequest.getRequest().getFile(),
                setupPathUrl(baseRequest.getRequest())
            );
            ResponseUploadFile responseUploadFile = new ResponseUploadFile();
            responseUploadFile.setUrlFile(
                setupUrlFile(baseRequest.getRequest(), fileName)
            );
            response.setResponse(responseUploadFile);
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                e.getMessage(),
                new ResponseUploadFile()
            );
        }
        return response;
    }

    public String setupPathUrl(RequestUploadFile request) {
        String pathFile = DefaultValues.emptyString;

        if (request.getFileType().equalsIgnoreCase(ParameterValues.file.type.image)) {
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.classes)) {
                pathFile = ParameterValues.file.path.image.classes;
            }
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.banners)) {
                pathFile = ParameterValues.file.path.image.banners;
            }
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.companies)) {
                pathFile = ParameterValues.file.path.image.companies;
            }
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.trainers)) {
                pathFile = ParameterValues.file.path.image.trainers;
            }
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.users)) {
                pathFile = ParameterValues.file.path.image.users;
            }
        }
        if (request.getFileType().equalsIgnoreCase(ParameterValues.file.type.document)) {
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.cv)) {
                pathFile = ParameterValues.file.path.document.cv;
            }
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.modul)) {
                pathFile = ParameterValues.file.path.document.modul;
            }
        }
        if (request.getFileType().equalsIgnoreCase(ParameterValues.file.type.video)) {
            pathFile = ParameterValues.file.path.video.course;
        }
        return pathFile;
    }

    public String setupUrlFile(RequestUploadFile request, String filename) {
        String urlFile = DefaultValues.emptyString;

        if (request.getFileType().equalsIgnoreCase(ParameterValues.file.type.image)) {
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.classes)) {
                urlFile = ParameterValues.file.url.image.classes + filename;
            }
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.banners)) {
                urlFile = ParameterValues.file.url.image.banners + filename;
            }
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.companies)) {
                urlFile = ParameterValues.file.url.image.companies + filename;
            }
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.trainers)) {
                urlFile = ParameterValues.file.url.image.trainers + filename;
            }
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.users)) {
                urlFile = ParameterValues.file.url.image.users + filename;
            }
        }
        if (request.getFileType().equalsIgnoreCase(ParameterValues.file.type.document)) {
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.cv)) {
                urlFile = ParameterValues.file.url.document.cv;
            }
            if (request.getGroupType().equalsIgnoreCase(ParameterValues.file.type.group.modul)) {
                urlFile = ParameterValues.file.url.document.modul;
            }
        }
        if (request.getFileType().equalsIgnoreCase(ParameterValues.file.type.video)) {
            urlFile = ParameterValues.file.url.video.course;
        }
        return urlFile;
    }
}
