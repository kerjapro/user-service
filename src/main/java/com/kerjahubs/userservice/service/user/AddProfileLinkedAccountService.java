package com.kerjahubs.userservice.service.user;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.AppsLinkedAccount;
import com.kerjahubs.common.enums.DocumentGroupType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.entity.parameter.DocumentType;
import com.kerjahubs.userservice.entity.user.UserDocument;
import com.kerjahubs.userservice.entity.user.UserLinkedAccount;
import com.kerjahubs.userservice.model.request.user.RequestAddProfileDokumen;
import com.kerjahubs.userservice.model.request.user.RequestAddProfileLinkedAccount;
import com.kerjahubs.userservice.repository.user.UserLinkedAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddProfileLinkedAccountService {

    @Autowired
    UserLinkedAccountRepository userLinkedAccountRepository;

    public BaseResponse<Object> addLinkedAccount(BaseRequest<RequestAddProfileLinkedAccount> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try{
            UserLinkedAccount userLinkedAccount = setupLinkedAccount(baseRequest.getRequest());
            userLinkedAccountRepository.save(userLinkedAccount);
            response.setResponseSuccess(
                MessageValues.success.title.general,
                MessageValues.success.message.user.add.linkedAccount.general
            );
        } catch (Exception e){
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.user.add.linkedAccount.general,
                DefaultValues.emptyString
            );
        }

        return response;
    }

    public UserLinkedAccount setupLinkedAccount(RequestAddProfileLinkedAccount request){
        UserLinkedAccount userLinkedAccount = new UserLinkedAccount();
        userLinkedAccount.setId(UUID.randomUUID().toString());
        userLinkedAccount.setCid(request.getCid());
        userLinkedAccount.setApps(AppsLinkedAccount.valueOf(request.getApps()));
        userLinkedAccount.setAppsCustomerEmail(request.getAppsCustomerEmail());
        userLinkedAccount.setAppsCustomerImage(request.getAppsCustomerImage());
        userLinkedAccount.setAppsCustomerName(request.getAppsCustomerName());
        userLinkedAccount.setAppsId(request.getAppsId());
        userLinkedAccount.setAppsToken(request.getAppsToken());
        userLinkedAccount.setGroupType(DocumentGroupType.valueOf(request.getGroupType()));
        return userLinkedAccount;
    }
}
