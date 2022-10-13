package com.kerjahubs.userservice.service.kerja;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.model.request.kelas.RequestAddBenefitProduct;
import com.kerjahubs.userservice.model.request.kerja.RequestKerjaBenefit;
import com.kerjahubs.userservice.repository.kerja.KerjaBenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageBenefitService {

    @Autowired
    KerjaBenefitRepository kerjaBenefitRepository;

    public BaseResponse<Object> manageBenefitKerja(BaseRequest<RequestKerjaBenefit> baseRequest){
        BaseResponse<Object> response = new BaseResponse<>();

        try{

        } catch (Exception e){
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general,
                DefaultValues.emptyString
            );
        }

        return response;
    }



}
