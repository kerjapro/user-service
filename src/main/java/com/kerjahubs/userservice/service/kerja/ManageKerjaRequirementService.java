package com.kerjahubs.userservice.service.kerja;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.userservice.entity.kerja.KerjaRequirement;
import com.kerjahubs.userservice.model.request.kerja.RequestKerjaRequirement;
import com.kerjahubs.userservice.repository.kerja.KerjaRequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManageKerjaRequirementService {
    @Autowired
    KerjaRequirementRepository kerjaRequirementRepository;

    public BaseResponse<Object> manageKerjaRequirement(BaseRequest<RequestKerjaRequirement> baseRequest){
        BaseResponse<Object> response = new BaseResponse<>();

        try{
            KerjaRequirement kerjaRequirement = setupKerjaRequirement(baseRequest.getRequest());
            kerjaRequirementRepository.saveAndFlush(kerjaRequirement);

            if(!baseRequest.getRequest().getStatus()){
                response.setResponseSuccess(
                    MessageValues.success.title.kerja.requirement.delete,
                    MessageValues.success.message.kerja.requirement.delete
                );
            }else{
                if(baseRequest.getRequest().getId().isEmpty()){
                    response.setResponseSuccess(
                        MessageValues.success.title.kerja.requirement.add,
                        MessageValues.success.message.kerja.requirement.add
                    );
                }else{
                    response.setResponseSuccess(
                        MessageValues.success.title.kerja.requirement.edit,
                        MessageValues.success.message.kerja.requirement.edit
                    );
                }
            }
        } catch (Exception e){
            response.setResponseError(
                MessageValues.error.title.general,
                e.getMessage(),
                DefaultValues.emptyString
            );
        }

        return response;
    }

    public KerjaRequirement setupKerjaRequirement(RequestKerjaRequirement request){
        KerjaRequirement kerjaRequirement = kerjaRequirementRepository.findById(request.getId()).orElseGet(KerjaRequirement::new);
        kerjaRequirement.setId(request.getId().isEmpty() ? UUID.randomUUID().toString() : request.getId());
        kerjaRequirement.setKerjaId(request.getJobid().isEmpty() ? kerjaRequirement.getKerjaId() : request.getJobid());
        kerjaRequirement.setName(request.getName().isEmpty() ? kerjaRequirement.getName() : request.getName());
        kerjaRequirement.setSequence(request.getSequence()==DefaultValues.emptyInteger ? kerjaRequirement.getSequence() : request.getSequence());
        kerjaRequirement.setStatus(request.getStatus());
        kerjaRequirement.setCreatedAt(request.getId().isEmpty() ? DateConversion.getDateNow(DateFormats.datetime) : kerjaRequirement.getCreatedAt());
        kerjaRequirement.setUpdatedAt(DateConversion.getDateNow(DateFormats.datetime));
        return kerjaRequirement;
    }
}
