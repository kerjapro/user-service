package com.kerjahubs.userservice.model.request.file;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestUploadFile {
    public MultipartFile file;
    public String fileType = DefaultValues.emptyString;
    public String groupType = DefaultValues.emptyString;
}
