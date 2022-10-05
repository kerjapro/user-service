package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.userservice.entity.KelasBenefit;
import com.kerjahubs.userservice.model.request.RequestAddBenefitProduct;
import com.kerjahubs.userservice.repository.KelasBenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddEditBenefitService {
    @Autowired
    KelasBenefitRepository kelasBenefitRepository;

    public BaseResponse<Object> addEditBenefitKelas(BaseRequest<RequestAddBenefitProduct> baseRequest){
        BaseResponse<Object> response = new BaseResponse<>();

        try{
            KelasBenefit benefit = setupBenefitKelas(baseRequest.getRequest());
            kelasBenefitRepository.saveAndFlush(benefit);
            if(!baseRequest.getRequest().getStatus()){
                response.setResponseSuccess(
                    MessageValues.success.title.kelas.benefit.delete,
                    MessageValues.success.message.kelas.benefit.delete
                );
            }else{
                if(baseRequest.getRequest().getId().isEmpty()){
                    response.setResponseSuccess(
                        MessageValues.success.title.kelas.benefit.add,
                        MessageValues.success.message.kelas.benefit.add
                    );
                }else{
                    response.setResponseSuccess(
                        MessageValues.success.title.kelas.benefit.edit,
                        MessageValues.success.message.kelas.benefit.edit
                    );
                }
            }
        }catch (Exception e){
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general
            );
        }
        return response;
    }

    public KelasBenefit setupBenefitKelas(RequestAddBenefitProduct request){
        KelasBenefit benefit = kelasBenefitRepository.findById(request.getId()).orElseGet(KelasBenefit::new);
        benefit.setId(request.getId().isEmpty() ? UUID.randomUUID().toString() : request.getId());
        benefit.setProductId(request.getProductId().isEmpty() ? benefit.getProductId() : request.getProductId());
        benefit.setBenefitName(request.getBenefitName().isEmpty() ? benefit.getBenefitName() : request.getBenefitName());
        benefit.setSequence(request.getSequence() == DefaultValues.emptyInteger ? benefit.getSequence() : request.getSequence());
        benefit.setStatus(request.getStatus());
        benefit.setCreatedAt(request.getId().isEmpty() ? DateConversion.getDateNow(DateFormats.datetime) : benefit.getCreatedAt());
        benefit.setUpdatedAt(DateConversion.getDateNow(DateFormats.datetime));
        return benefit;
    }
}
