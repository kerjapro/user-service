package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.ModulType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.userservice.entity.KelasModul;
import com.kerjahubs.userservice.model.request.RequestAddModuleProduct;
import com.kerjahubs.userservice.repository.KelasModulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddEditModulService {
    @Autowired
    KelasModulRepository kelasModulRepository;

    public BaseResponse<Object> addEditModulKelas(BaseRequest<RequestAddModuleProduct> baseRequest){
        BaseResponse<Object> response = new BaseResponse<>();

        try{
            KelasModul modul = setupModulKelas(baseRequest.getRequest());
            kelasModulRepository.saveAndFlush(modul);
            if(!baseRequest.getRequest().getStatus()){
                response.setResponseSuccess(
                    MessageValues.success.title.kelas.modul.delete,
                    MessageValues.success.message.kelas.modul.delete
                );
            }else{
                if(baseRequest.getRequest().getId().isEmpty()){
                    response.setResponseSuccess(
                        MessageValues.success.title.kelas.modul.add,
                        MessageValues.success.message.kelas.modul.add
                    );
                }else{
                    response.setResponseSuccess(
                        MessageValues.success.title.kelas.modul.edit,
                        MessageValues.success.message.kelas.modul.edit
                    );
                }
            }
        }catch (Exception e){
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general,
                DefaultValues.emptyString
            );
        }
        return response;
    }

    public KelasModul setupModulKelas(RequestAddModuleProduct request){
        KelasModul modul = kelasModulRepository.findById(request.getId()).orElseGet(KelasModul::new);
        modul.setId(request.getId().isEmpty() ? UUID.randomUUID().toString() : request.getId());
        modul.setKelasId(request.getProductId().isEmpty() ? modul.getKelasId() : request.getProductId());
        modul.setModulType(request.getModulType().isEmpty() ? modul.getModulType(): ModulType.valueOf(request.getModulType()));
        modul.setModulName(request.getModulName().isEmpty() ? modul.getModulName() : request.getModulName());
        modul.setModulDesc(request.getModulDesc().isEmpty() ? modul.getModulDesc() : request.getModulDesc());
        modul.setFile(request.getFile().isEmpty() ? modul.getFile() : request.getFile());
        modul.setSequence(request.getSequence() == DefaultValues.emptyInteger ? modul.getSequence() : request.getSequence());
        modul.setStatus(request.getStatus());
        modul.setCreatedAt(request.getId().isEmpty() ? DateConversion.getDateNow(DateFormats.datetime) : modul.getCreatedAt());
        modul.setUpdatedAt(DateConversion.getDateNow(DateFormats.datetime));
        return modul;
    }
}
