package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.FormatValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.Gender;
import com.kerjahubs.common.enums.UserType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.common.utility.StringConversion;
import com.kerjahubs.userservice.entity.UserBase;
import com.kerjahubs.userservice.model.request.RequestRegister;
import com.kerjahubs.userservice.repository.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegisterUserRetailService {

    @Autowired
    UserBaseRepository userBaseRepository;

    public BaseResponse<Object> registerUserRetail(BaseRequest<RequestRegister> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try {
            if(userBaseRepository.existsByEmail(baseRequest.getRequest().getEmail())){
                response.setResponseError(
                    MessageValues.error.title.registerUserRetail,
                    MessageValues.error.message.existEmailUserRetail
                );
                return response;
            }

            if(userBaseRepository.existsByPhoneNumber(baseRequest.getRequest().getPhoneNumber())){
                response.setResponseError(
                    MessageValues.error.title.registerUserRetail,
                    MessageValues.error.message.existPhoneUserRetail
                );
                return response;
            }

            UserBase userBase = setupUserBase(baseRequest.getRequest());
            userBaseRepository.save(userBase);
            response.setResponseSuccess(
                MessageValues.success.title.registerUserRetail,
                MessageValues.success.message.registerUserRetail
            );
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.registerUserRetail,
                MessageValues.error.message.registerUserRetail
            );
        }
        return response;
    }

    public UserBase setupUserBase(RequestRegister request) {
        List<UserBase> listUser = userBaseRepository.findAll();
        int totalUser = listUser.size() + 1;

        UserBase userBase = new UserBase();
        userBase.setCid(
            String.format(
                FormatValues.cid,
                DateConversion.toString(new Date(), DateFormats.id),
                StringConversion.leftPad(String.valueOf(totalUser), FormatValues.leftPadCid, FormatValues.padList)
            )
        );
        userBase.setFullName(request.getFullName());
        userBase.setGender(Gender.valueOf(Gender.PRIA.getCode()));
        userBase.setEmail(request.getEmail());
        userBase.setPhoneNumber(request.getPhoneNumber());
        userBase.setPassword(request.getPassword());
        userBase.setType(UserType.valueOf(request.getType()));

        return userBase;
    }
}
