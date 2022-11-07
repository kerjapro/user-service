package com.kerjahubs.userservice.service.user;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.userservice.entity.user.UserBase;
import com.kerjahubs.userservice.model.request.user.RequestChangePassword;
import com.kerjahubs.userservice.repository.user.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {
    @Autowired
    UserBaseRepository userBaseRepository;

    public BaseResponse<Object> changePassword(BaseRequest<RequestChangePassword> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try {
            UserBase userBase = userBaseRepository.findByIdAndPassword(
                baseRequest.getRequest().getCid(),
                baseRequest.getRequest().getOldPassword()
            ).orElseGet(UserBase::new);

            if (userBase.getCid().isEmpty()) {
                response.setResponseSuccess(
                    MessageValues.error.title.general,
                    MessageValues.error.message.user.password.wrong
                );
                return response;
            }

            userBase.setPassword(baseRequest.getRequest().getNewPassword());
            userBase.setUpdatedAt(DateConversion.getDateNow(DateFormats.datetime));
            userBaseRepository.saveAndFlush(userBase);
            response.setResponseSuccess(
                MessageValues.success.title.general,
                MessageValues.success.message.user.password.change
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
}
