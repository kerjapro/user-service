package com.kerjahubs.userservice.service.register;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.Gender;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.common.utility.StringConversion;
import com.kerjahubs.userservice.entity.user.UserBase;
import com.kerjahubs.userservice.model.request.user.RequestEditProfileDataRetail;
import com.kerjahubs.userservice.repository.user.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditProfileRetailService {
    @Autowired
    UserBaseRepository userBaseRepository;

    public BaseResponse<Object> editProfile(BaseRequest<RequestEditProfileDataRetail> baseRequest){
        BaseResponse<Object> response = new BaseResponse<>();

        try{
            UserBase userBase = setupUserBase(baseRequest.getRequest());
            userBaseRepository.saveAndFlush(userBase);
            response.setResponseSuccess(
                MessageValues.success.title.general,
                MessageValues.success.message.general
            );
        } catch (Exception e){
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general,
                DefaultValues.emptyString
            );
        }
        return response;
    }

    public UserBase setupUserBase(RequestEditProfileDataRetail request){
        UserBase userBase = userBaseRepository.findById(request.getCid()).orElseGet(UserBase::new);
        userBase.setCid(
            request.getCid().isEmpty() ? userBase.getCid() : request.getCid()
        );
        userBase.setEmail(
            request.getEmail().isEmpty() ? userBase.getEmail() : request.getEmail()
        );
        userBase.setPhoneNumber(
            request.getPhoneNumber().isEmpty() ? userBase.getPhoneNumber() : request.getPhoneNumber()
        );
        userBase.setFullName(
            request.getRetailData().getFullname().isEmpty() ? userBase.getFullName() : request.getRetailData().getFullname()
        );
        userBase.setGender(
            request.getRetailData().getGender().isEmpty() ? userBase.getGender() : Gender.valueOf(request.getRetailData().getGender())
        );
        userBase.setBirthDate(
            request.getRetailData().getBirthDate().isEmpty()
                ? userBase.getBirthDate()
                : DateConversion.toDate(request.getRetailData().getBirthDate(), DateFormats.birthdate)
        );
        userBase.setPreferences(
            StringConversion.listToString(request.getRetailData().getPreferences())
        );
        userBase.setSectors(
            StringConversion.listToString(request.getRetailData().getSectors())
        );
        return userBase;
    }
}
