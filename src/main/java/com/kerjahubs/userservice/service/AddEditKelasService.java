package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
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

import java.math.BigDecimal;

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
        kelas.setPartnerId(request.getPartnerId().isEmpty() ? kelas.getPartnerId() : request.getPartnerId());
        kelas.setSectorId(request.getSectorId().isEmpty() ? kelas.getSectorId() : request.getSectorId());
        kelas.setSectorSubId(request.getSectorSubId().isEmpty() ? kelas.getSectorSubId() : request.getSectorSubId());
        kelas.setKelasType(request.getProductType().isEmpty() ? kelas.getKelasType() : KelasType.valueOf(KelasType.valueOf(request.getProductType()).getName()));
        kelas.setKelasName(request.getProductName().isEmpty() ? kelas.getKelasName() : request.getProductName());
        kelas.setKelasDesc(request.getProductDesc().isEmpty() ? kelas.getKelasDesc() : request.getProductDesc());
        kelas.setPrice(request.getPrice().equals(BigDecimal.ZERO) ? kelas.getPrice() : request.getPrice());
        kelas.setSeat(request.getSeat()==DefaultValues.emptyInteger ? kelas.getSeat() : request.getSeat());
        kelas.setUrl(request.getUrl().isEmpty() ? kelas.getUrl() : request.getUrl());
        kelas.setImage(request.getImage().isEmpty() ? kelas.getImage() : request.getImage());
        kelas.setLanguage(request.getLanguage().isEmpty() ? kelas.getLanguage() : request.getLanguage());
        kelas.setEventDate(request.getEventDate().isEmpty() ? kelas.getEventDate() : DateConversion.toDate(request.getEventDate(), DateFormats.datetime));
        kelas.setExpiredDate(request.getExpiredDate().isEmpty() ? kelas.getExpiredDate() : DateConversion.toDate(request.getExpiredDate(), DateFormats.datetime));
        kelas.setCreatedAt(request.getId().isEmpty() ? DateConversion.getDateNow(DateFormats.datetime) : kelas.getCreatedAt());
        kelas.setUpdatedAt(DateConversion.getDateNow(DateFormats.datetime));
        kelas.setStatus(request.getStatus());
        return kelas;
    }
}
