package com.kerjahubs.userservice.service.user;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.DocumentGroupType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.entity.parameter.DocumentType;
import com.kerjahubs.userservice.entity.user.UserDocument;
import com.kerjahubs.userservice.model.request.user.RequestAddProfileDokumen;
import com.kerjahubs.userservice.repository.parameters.DocumentTypeRepository;
import com.kerjahubs.userservice.repository.user.UserDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddProfileDocumentService {
    @Autowired
    UserDocumentRepository userDocumentRepository;

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    public BaseResponse<Object> addDocument(BaseRequest<RequestAddProfileDokumen> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try {
            UserDocument userDocument = setupDocument(baseRequest.getRequest());
            if (userDocument.getDocumentType().getId().isEmpty()) {
                response.setResponseError(
                    MessageValues.error.title.general,
                    MessageValues.error.message.typeNotFound,
                    DefaultValues.emptyString
                );
                return response;
            }

            userDocumentRepository.save(userDocument);
            response.setResponseSuccess(
                MessageValues.success.title.general,
                MessageValues.success.message.user.add.document.general
            );
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.user.add.document.general,
                DefaultValues.emptyString
            );
        }
        return response;
    }

    public UserDocument setupDocument(RequestAddProfileDokumen request) {
        UserDocument userDocument = new UserDocument();
        userDocument.setId(UUID.randomUUID().toString());
        userDocument.setCid(request.getCid());
        userDocument.setGroupType(DocumentGroupType.valueOf(request.getGroupType()));
        userDocument.setValue(request.getValue());
        userDocument.setDocumentType(documentTypeRepository.findIdByName(request.getType()).orElseGet(DocumentType::new));
        return userDocument;
    }
}
