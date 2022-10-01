package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.FormatValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.AppsLinkedAccount;
import com.kerjahubs.common.enums.UserType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.common.utility.StringConversion;
import com.kerjahubs.userservice.entity.DocumentType;
import com.kerjahubs.userservice.entity.UserBase;
import com.kerjahubs.userservice.entity.UserLinkedAccount;
import com.kerjahubs.userservice.entity.UserPartner;
import com.kerjahubs.userservice.model.OtherAccount;
import com.kerjahubs.userservice.model.request.RequestRegister;
import com.kerjahubs.userservice.repository.UserBaseRepository;
import com.kerjahubs.userservice.repository.UserLinkedAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RegisterUserRetailService {

    @Autowired
    UserBaseRepository userBaseRepository;

    @Autowired
    UserLinkedAccountRepository userLinkedAccountRepository;

    public BaseResponse<Object> registerUserRetail(BaseRequest<RequestRegister> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try {
            DocumentType documentType = new DocumentType();
            if (!baseRequest.getRequest().getEmail().isEmpty()
                && userBaseRepository.existsByEmail(baseRequest.getRequest().getEmail())
            ) {
                response.setResponseError(
                    MessageValues.error.title.baseError,
                    MessageValues.error.message.registerUser.existEmail
                );
                return response;
            }

            if (!baseRequest.getRequest().getPhoneNumber().isEmpty()
                && userBaseRepository.existsByPhoneNumber(baseRequest.getRequest().getPhoneNumber())
            ) {
                response.setResponseError(
                    MessageValues.error.title.baseError,
                    MessageValues.error.message.registerUser.existPhone
                );
                return response;
            }

            UserBase userBase = setupUserBase(baseRequest.getRequest());
            if (baseRequest.getRequest().getIsUsingOtherAccount()) {
                if (!baseRequest.getRequest().getOtherAccount().getType().isEmpty()
                    && !baseRequest.getRequest().getOtherAccount().getEmail().isEmpty()
                    && userLinkedAccountRepository.existsOtherAccount(
                    baseRequest.getRequest().getOtherAccount().getType(),
                    baseRequest.getRequest().getOtherAccount().getEmail())
                ) {
                    response.setResponseError(
                        MessageValues.error.title.baseError,
                        MessageValues.error.message.registerUser.existOtherAccount
                    );
                    return response;
                }

                UserLinkedAccount userLinkedAccount = setupUserLinkedAccount(
                    baseRequest.getRequest().getOtherAccount(),
                    userBase
                );
                userLinkedAccountRepository.save(userLinkedAccount);
            }
            userBaseRepository.save(userBase);
            response.setResponseSuccess(
                MessageValues.success.title.baseSuccess,
                MessageValues.success.message.baseSuccess
            );
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.baseError,
                MessageValues.error.message.baseError
            );
        }
        return response;
    }

    public UserBase setupUserBase(RequestRegister request) {
        int totalUser = (int) (userBaseRepository.count()+1);

        UserBase userBase = new UserBase();
        userBase.setCid(
            String.format(
                FormatValues.cid,
                DateConversion.toString(new Date(), DateFormats.id),
                StringConversion.leftPad(String.valueOf(totalUser), FormatValues.leftPadCid, FormatValues.padList)
            )
        );
        userBase.setFullName(request.getFullName());
        userBase.setEmail(request.getEmail());
        userBase.setPhoneNumber(request.getPhoneNumber());
        userBase.setPassword(request.getPassword());
        userBase.setType(UserType.valueOf(request.getType()));

        return userBase;
    }

    public UserLinkedAccount setupUserLinkedAccount(
        OtherAccount otherAccount,
        UserBase userBase
    ) {
        UserLinkedAccount userLinkedAccount = new UserLinkedAccount();
        userLinkedAccount.setId(
            UUID.randomUUID().toString()
        );
        userLinkedAccount.setCid(userBase.getCid());
        userLinkedAccount.setApps(AppsLinkedAccount.valueOf(otherAccount.getType()));
        userLinkedAccount.setAppsId(otherAccount.getId());
        userLinkedAccount.setAppsCustomerName(otherAccount.getName());
        userLinkedAccount.setAppsCustomerImage(otherAccount.getImage());
        userLinkedAccount.setAppsCustomerEmail(otherAccount.getEmail());
        userLinkedAccount.setAppsToken(otherAccount.getToken());
        return userLinkedAccount;
    }
}
