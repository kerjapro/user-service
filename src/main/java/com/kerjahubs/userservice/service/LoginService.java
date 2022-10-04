package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.UserType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.common.utility.StringConversion;
import com.kerjahubs.userservice.entity.UserBase;
import com.kerjahubs.userservice.model.Documents;
import com.kerjahubs.userservice.model.PartnerData;
import com.kerjahubs.userservice.model.RetailData;
import com.kerjahubs.userservice.model.request.RequestLogin;
import com.kerjahubs.userservice.model.response.ResponseLogin;
import com.kerjahubs.userservice.repository.UserBaseRepository;
import com.kerjahubs.userservice.repository.UserLinkedAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    UserBaseRepository userBaseRepository;

    @Autowired
    UserLinkedAccountRepository userLinkedAccountRepository;

    public BaseResponse<ResponseLogin> login(BaseRequest<RequestLogin> baseRequest) {
        BaseResponse<ResponseLogin> response = new BaseResponse<>();

        try {
            if (!baseRequest.getRequest().getUsername().isEmpty()
                && !userBaseRepository.existsByEmail(baseRequest.getRequest().getUsername())
                && !userBaseRepository.existsByPhoneNumber(baseRequest.getRequest().getUsername())
            ) {
                response.setResponseError(
                    MessageValues.error.title.baseError,
                    MessageValues.error.message.login.notFound
                );
                return response;
            }

            if (!baseRequest.getRequest().getPassword().isEmpty()
                && !userBaseRepository.existsByPassword(baseRequest.getRequest().getPassword())
            ) {
                response.setResponseError(
                    MessageValues.error.title.baseError,
                    MessageValues.error.message.login.wrongPassword
                );
                return response;
            }

            ResponseLogin dataResponse = setupResponseLogin(baseRequest.getRequest());


        } catch (Exception e) {

        }
        return response;
    }

    public ResponseLogin setupResponseLogin(RequestLogin request) {
        ResponseLogin responseLogin = new ResponseLogin();

        UserBase userBase = userBaseRepository.findByUsernameAndPassword(
            request.getUsername(),
            request.getPassword()
        ).orElseGet(UserBase::new);

        responseLogin.setCid(userBase.getCid());
        responseLogin.setEmail(userBase.getEmail());
        responseLogin.setPhoneNumber(userBase.getPhoneNumber());
        responseLogin.setType(userBase.getType().toString());
        responseLogin.setIsVerified(userBase.getIsVerified());
        responseLogin.setLastLogin(DateConversion.toString(userBase.getLastLogin(), DateFormats.datetime));
        responseLogin.setRetailData(setupRetailData(request, userBase));
        responseLogin.setPartnerData(setupPartnerData(request, userBase));
        responseLogin.setSessionId(UUID.randomUUID().toString());
        return responseLogin;
    }

    public RetailData setupRetailData(
        RequestLogin request,
        UserBase userBase
    ) {
        RetailData retailData = new RetailData();
        retailData.setFullname(userBase.getFullName());
        retailData.setGender(userBase.getGender().toString());
        retailData.setBirthDate(DateConversion.toString(userBase.getBirthDate(), DateFormats.birthdate));
        retailData.setPreferences(StringConversion.stringToList(userBase.getPreferences()));
        retailData.setSectors(StringConversion.stringToList(userBase.getSectors()));
        return retailData;
    }

    public List<PartnerData> setupPartnerData(
        RequestLogin requestLogin,
        UserBase userBase
    ){
        List<PartnerData> listPartnerData = new ArrayList<>();
        PartnerData partnerData = new PartnerData();

        return listPartnerData;
    }
}
