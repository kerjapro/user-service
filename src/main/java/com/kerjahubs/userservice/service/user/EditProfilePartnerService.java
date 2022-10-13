package com.kerjahubs.userservice.service.user;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.entity.user.UserPartner;
import com.kerjahubs.userservice.model.request.user.RequestEditProfileDataPartner;
import com.kerjahubs.userservice.repository.user.UserPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditProfilePartnerService {
    @Autowired
    UserPartnerRepository userPartnerRepository;

    public BaseResponse<Object> editProfilePartner(BaseRequest<RequestEditProfileDataPartner> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try {
            UserPartner userPartner = setupUserPartner(baseRequest.getRequest());
            if(userPartner.getPartnerId().isEmpty()){
                response.setResponseSuccess(
                    MessageValues.error.title.general,
                    MessageValues.error.message.notFound
                );
                return response;
            }

            userPartnerRepository.saveAndFlush(userPartner);
            response.setResponseSuccess(
                MessageValues.success.title.general,
                MessageValues.success.message.user.edit.general
            );
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.user.edit.general,
                DefaultValues.emptyString
            );
        }
        return response;
    }

    public UserPartner setupUserPartner(RequestEditProfileDataPartner request){
        UserPartner userPartner = userPartnerRepository.findById(request.getPartnerId()).orElseGet(UserPartner::new);
        userPartner.setPartnerName(
            request.getPartnerName().isEmpty() ? userPartner.getPartnerName() : request.getPartnerName()
        );
        userPartner.setAbout(
            request.getAbout().isEmpty() ? userPartner.getAbout() : request.getAbout()
        );
        userPartner.setTagline(
            request.getTagline().isEmpty() ? userPartner.getTagline() : request.getTagline()
        );
        userPartner.setWebsite(
            request.getWebsite().isEmpty() ? userPartner.getWebsite() : request.getWebsite()
        );
        userPartner.setProvinceId(
            request.getProvinceId().isEmpty() ? userPartner.getProvinceId() : request.getProvinceId()
        );
        userPartner.setProvinceName(
            request.getProvinceName().isEmpty() ? userPartner.getProvinceName() : request.getProvinceName()
        );
        userPartner.setDistrictId(
            request.getDistrictId().isEmpty() ? userPartner.getDistrictId() : request.getDistrictId()
        );
        userPartner.setDistrictName(
            request.getDistrictName().isEmpty() ? userPartner.getDistrictName() : request.getDistrictName()
        );
        userPartner.setAddress(
            request.getAddress().isEmpty() ? userPartner.getAddress() : request.getAddress()
        );
        userPartner.setPostalCode(
            request.getPostalCode().isEmpty() ? userPartner.getPostalCode() : request.getPostalCode()
        );
        userPartner.setMaps(
            request.getMaps().isEmpty() ? userPartner.getMaps() : request.getMaps()
        );
        return userPartner;
    }

}
