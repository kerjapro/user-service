package com.kerjahubs.userservice.service.kelas;

import com.kerjahubs.common.constant.*;
import com.kerjahubs.common.enums.KelasType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.common.utility.StringConversion;
import com.kerjahubs.userservice.entity.kelas.Kelas;
import com.kerjahubs.userservice.model.request.kelas.RequestAddProduct;
import com.kerjahubs.userservice.repository.kelas.KelasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ManageKelasService {
    @Autowired
    KelasRepository kelasRepository;

    public BaseResponse<Object> addEditKelas(BaseRequest<RequestAddProduct> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try {
            Kelas kelas = setupKelas(baseRequest.getRequest());
            kelasRepository.saveAndFlush(kelas);
            if (!baseRequest.getRequest().getStatus()) {
                response.setResponseSuccess(
                    MessageValues.success.title.kelas.delete,
                    MessageValues.success.message.kelas.delete
                );
            } else {
                if (baseRequest.getRequest().getId().isEmpty()) {
                    response.setResponseSuccess(
                        MessageValues.success.title.kelas.add,
                        MessageValues.success.message.kelas.add
                    );
                } else {
                    response.setResponseSuccess(
                        MessageValues.success.title.kelas.edit,
                        MessageValues.success.message.kelas.edit
                    );
                }
            }
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general,
                DefaultValues.emptyString
            );
        }
        return response;
    }

    public Kelas setupKelas(RequestAddProduct request) {
        int runningPartner = kelasRepository.runningNumberPartner(request.getProductType()) + 1;

        Kelas kelas = kelasRepository.findById(request.getId()).orElseGet(Kelas::new);

        if (kelas.getId().isEmpty()) {
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
        kelas.setSeat(request.getSeat() == DefaultValues.emptyInteger ? kelas.getSeat() : request.getSeat());
        kelas.setUrl(setupUrlKelas(request.getProductName(), kelas.getId()));
        kelas.setImage(request.getImage().isEmpty() ? kelas.getImage() : request.getImage());
        kelas.setLanguage(request.getLanguage().isEmpty() ? kelas.getLanguage() : request.getLanguage());
        kelas.setEventDate(request.getEventDate().isEmpty() ? kelas.getEventDate() : DateConversion.toDate(request.getEventDate(), DateFormats.datetime));
        kelas.setExpiredDate(request.getExpiredDate().isEmpty() ? kelas.getExpiredDate() : DateConversion.toDate(request.getExpiredDate(), DateFormats.datetime));
        kelas.setCreatedAt(request.getId().isEmpty() ? DateConversion.getDateNow(DateFormats.datetime) : kelas.getCreatedAt());
        kelas.setUpdatedAt(DateConversion.getDateNow(DateFormats.datetime));
        kelas.setStatus(request.getStatus());
        kelas.setViews(DefaultValues.emptyInteger);
        return kelas;
    }

    public String setupUrlKelas(String name, String productId) {
        String url = DefaultValues.emptyString;
        if (!name.isEmpty()) {
            url = name.toLowerCase().replaceAll(RegexCodes.removeSpecialCharacter, RegexCodes.space);
            url = url.replaceAll(RegexCodes.space, RegexCodes.hyphen);
        }
        return String.format(
            FormatValues.url,
            url,
            productId
        ).toLowerCase();
    }
}
