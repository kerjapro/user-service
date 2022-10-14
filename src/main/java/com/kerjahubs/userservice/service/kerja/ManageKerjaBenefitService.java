package com.kerjahubs.userservice.service.kerja;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.userservice.entity.kerja.KerjaBenefit;
import com.kerjahubs.userservice.model.request.kerja.RequestKerjaBenefit;
import com.kerjahubs.userservice.repository.kerja.KerjaBenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManageKerjaBenefitService {

    @Autowired
    KerjaBenefitRepository kerjaBenefitRepository;

    public BaseResponse<Object> manageKerjaBenefit(BaseRequest<RequestKerjaBenefit> baseRequest){
        BaseResponse<Object> response = new BaseResponse<>();

        try{
            KerjaBenefit kerjaBenefit = setupKerjaBenefit(baseRequest.getRequest());
            kerjaBenefitRepository.saveAndFlush(kerjaBenefit);

            if(!baseRequest.getRequest().getStatus()){
                response.setResponseSuccess(
                    MessageValues.success.title.kerja.benefit.delete,
                    MessageValues.success.message.kerja.benefit.delete
                );
            }else{
                if(baseRequest.getRequest().getId().isEmpty()){
                    response.setResponseSuccess(
                        MessageValues.success.title.kerja.benefit.add,
                        MessageValues.success.message.kerja.benefit.add
                    );
                }else{
                    response.setResponseSuccess(
                        MessageValues.success.title.kerja.benefit.edit,
                        MessageValues.success.message.kerja.benefit.edit
                    );
                }
            }
        } catch (Exception e){
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general,
                DefaultValues.emptyString
            );
        }

        return response;
    }

    public KerjaBenefit setupKerjaBenefit(RequestKerjaBenefit request){
        KerjaBenefit kerjaBenefit = kerjaBenefitRepository.findById(request.getId()).orElseGet(KerjaBenefit::new);
        kerjaBenefit.setId(request.getId().isEmpty() ? UUID.randomUUID().toString() : request.getId());
        kerjaBenefit.setKerjaId(request.getJobid().isEmpty() ? kerjaBenefit.getKerjaId() : request.getJobid());
        kerjaBenefit.setName(request.getName().isEmpty() ? kerjaBenefit.getName() : request.getName());
        kerjaBenefit.setSequence(request.getSequence()==DefaultValues.emptyInteger ? kerjaBenefit.getSequence() : request.getSequence());
        kerjaBenefit.setStatus(request.getStatus());
        kerjaBenefit.setCreatedAt(request.getId().isEmpty() ? DateConversion.getDateNow(DateFormats.datetime) : kerjaBenefit.getCreatedAt());
        kerjaBenefit.setUpdatedAt(DateConversion.getDateNow(DateFormats.datetime));
        return kerjaBenefit;
    }
}
