package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.FormatValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.common.utility.StringConversion;
import com.kerjahubs.userservice.entity.UserBase;
import com.kerjahubs.userservice.model.request.RequestRegister;
import com.kerjahubs.userservice.repository.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterUserRetailService {

    @Autowired
    UserBaseRepository userBaseRepository;

    public BaseResponse<Object> registerUserRetail(BaseRequest<RequestRegister> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try {
            if (!baseRequest.getRequest().getEmail().isEmpty()
                && userBaseRepository.existsByEmail(baseRequest.getRequest().getEmail())
            ) {
                response.setResponseError(
                    MessageValues.error.title.register.general,
                    MessageValues.error.message.register.user.existEmail
                );
                return response;
            }

            if (!baseRequest.getRequest().getPhoneNumber().isEmpty()
                && userBaseRepository.existsByPhoneNumber(baseRequest.getRequest().getPhoneNumber())
            ) {
                response.setResponseError(
                    MessageValues.error.title.register.general,
                    MessageValues.error.message.register.user.existPhone
                );
                return response;
            }

            UserBase userBase = setupUserBase(baseRequest.getRequest());
            userBaseRepository.save(userBase);
            response.setResponseSuccess(
                MessageValues.success.title.register.general,
                MessageValues.success.message.register.user
            );
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general
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
        userBase.setPlatformRegister(request.getRegistrationPlatform());

        return userBase;
    }
}
