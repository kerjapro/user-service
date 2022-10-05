package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.FormatValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.KelasType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.common.utility.StringConversion;
import com.kerjahubs.userservice.entity.Kelas;
import com.kerjahubs.userservice.model.request.RequestAddProduct;
import com.kerjahubs.userservice.repository.KelasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddEditKelasService {
    @Autowired
    KelasRepository kelasRepository;

    public BaseResponse<Object> addEditKelas(BaseRequest<RequestAddProduct> baseRequest){
        BaseResponse<Object> response = new BaseResponse<>();

        try{
            Kelas kelas = setupKelas(baseRequest.getRequest());
            if(!baseRequest.getRequest().getStatus()){
                kelasRepository.delete(kelas);
                response.setResponseSuccess(
                    MessageValues.success.title.deleteKelas,
                    MessageValues.success.message.kelas.deleteKelas
                );
            }else{
                kelasRepository.saveAndFlush(kelas);
                if(baseRequest.getRequest().getId().isEmpty()){
                    response.setResponseSuccess(
                        MessageValues.success.title.addKelas,
                        MessageValues.success.message.kelas.addKelas
                    );
                }
                response.setResponseSuccess(
                    MessageValues.success.title.editKelas,
                    MessageValues.success.message.kelas.editKelas
                );
            }
        } catch (Exception e){
            if(!baseRequest.getRequest().getStatus()){
                response.setResponseError(
                    MessageValues.error.title.deleteKelas,
                    MessageValues.error.message.kelas.deleteKelas
                );
            }else{
                if(baseRequest.getRequest().getId().isEmpty()){
                    response.setResponseError(
                        MessageValues.error.title.addKelas,
                        MessageValues.error.message.kelas.addKelas
                    );
                }
                response.setResponseError(
                    MessageValues.error.title.editKelas,
                    MessageValues.error.message.kelas.editKelas
                );
            }
        }
        return response;
    }

    public Kelas setupKelas(RequestAddProduct request){
        int runningPartner = kelasRepository.runningNumberPartner(request.getProductType())+1;

        Kelas kelas = kelasRepository.findById(request.getId()).orElseGet(Kelas::new);

        if(kelas.getId().isEmpty()){
            kelas.setId(
                String.format(
                    FormatValues.kelasId,
                    KelasType.valueOf(request.getProductType()).getCode(),
                    StringConversion.leftPad(String.valueOf(runningPartner), FormatValues.leftPadKelas, FormatValues.padList)
                )
            );
        }
        kelas.setPartnerId(request.getPartnerId());
        kelas.setSectorId(request.getSectorId());
        kelas.setSectorSubId(request.getSectorSubId());
        kelas.setKelasType(KelasType.valueOf(KelasType.valueOf(request.getProductType()).getName()));
        kelas.setKelasName(request.getProductName());
        kelas.setKelasDesc(request.getProductDesc());
        kelas.setPrice(request.getPrice());
        kelas.setSeat(request.getSeat());
        kelas.setUrl(request.getUrl());
        kelas.setImage(request.getImage());
        kelas.setLanguage(request.getLanguage());
        kelas.setEventDate(DateConversion.toDate(request.getEventDate(), DateFormats.datetime));
        kelas.setExpiredDate(DateConversion.toDate(request.getExpiredDate(), DateFormats.datetime));
        kelas.setCreatedAt(DateConversion.getDateNow(DateFormats.datetime));
        kelas.setUpdatedAt(DateConversion.getDateNow(DateFormats.datetime));
        kelas.setStatus(request.getStatus());
        return kelas;
    }
}
