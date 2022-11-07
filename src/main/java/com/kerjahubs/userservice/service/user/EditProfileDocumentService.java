package com.kerjahubs.userservice.service.user;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.entity.user.UserDocument;
import com.kerjahubs.userservice.model.request.user.RequestEditProfileDocument;
import com.kerjahubs.userservice.repository.user.UserDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditProfileDocumentService {

    @Autowired
    UserDocumentRepository userDocumentRepository;

    public BaseResponse<Object> editDocument(BaseRequest<RequestEditProfileDocument> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try {
            UserDocument userDocument = setupDocument(baseRequest.getRequest());
            if (userDocument.getId().isEmpty()) {
                response.setResponseError(
                    MessageValues.error.title.general,
                    MessageValues.error.message.user.edit.general,
                    DefaultValues.emptyString
                );
                return response;
            }

            userDocumentRepository.saveAndFlush(userDocument);
            response.setResponseSuccess(
                MessageValues.success.title.general,
                MessageValues.success.message.user.edit.general
            );
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                e.getMessage(),
                DefaultValues.emptyString
            );
        }
        return response;
    }

    public UserDocument setupDocument(RequestEditProfileDocument request) {
        UserDocument userDocument = userDocumentRepository.findById(request.getId()).orElseGet(UserDocument::new);
        userDocument.setValue(
            request.getValue().isEmpty() ? userDocument.getValue() : request.getValue()
        );
        return userDocument;
    }
}
