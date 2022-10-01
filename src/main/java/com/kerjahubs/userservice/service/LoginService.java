package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.model.request.RequestLogin;
import com.kerjahubs.userservice.model.response.ResponseLogin;
import com.kerjahubs.userservice.repository.UserBaseRepository;
import com.kerjahubs.userservice.repository.UserLinkedAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



        } catch (Exception e) {

        }
        return response;
    }
}
