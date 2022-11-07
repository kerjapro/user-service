package com.kerjahubs.userservice.service.kerja;

import com.kerjahubs.common.constant.*;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.common.utility.StringConversion;
import com.kerjahubs.userservice.entity.kerja.Kerja;
import com.kerjahubs.userservice.model.request.kerja.RequestKerja;
import com.kerjahubs.userservice.repository.kerja.KerjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ManageKerjaService {

    @Autowired
    KerjaRepository kerjaRepository;

    public BaseResponse<Object> manageKerja(BaseRequest<RequestKerja> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try {
            Kerja kerja = setupKerja(baseRequest.getRequest());
            kerjaRepository.saveAndFlush(kerja);
            if (!baseRequest.getRequest().getStatus()) {
                response.setResponseSuccess(
                    MessageValues.success.title.kerja.delete,
                    MessageValues.success.message.kerja.delete
                );
            } else {
                if (baseRequest.getRequest().getId().isEmpty()) {
                    response.setResponseSuccess(
                        MessageValues.success.title.kerja.add,
                        MessageValues.success.message.kerja.add
                    );
                } else {
                    response.setResponseSuccess(
                        MessageValues.success.title.kerja.edit,
                        MessageValues.success.message.kerja.edit
                    );
                }
            }
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                e.getMessage(),
                DefaultValues.emptyString
            );
        }
        return response;
    }

    public Kerja setupKerja(RequestKerja request) {
        int runningPartner = (int) (kerjaRepository.count() + 1);

        Kerja kerja = kerjaRepository.findById(request.getId()).orElseGet(Kerja::new);

        if (kerja.getId().isEmpty()) {
            kerja.setId(
                String.format(
                    FormatValues.kerjaId,
                    StringConversion.leftPad(String.valueOf(runningPartner), FormatValues.leftPadKerja, FormatValues.padList)
                )
            );
        }
        kerja.setTitle(request.getTitle().isEmpty() ? kerja.getTitle() : request.getTitle());
        kerja.setDescription(request.getDescription().isEmpty() ? kerja.getDescription() : request.getDescription());
        kerja.setSalaryStart(request.getSalaryStart().equals(BigDecimal.ZERO) ? kerja.getSalaryStart() : request.getSalaryStart());
        kerja.setSalaryEnd(request.getSalaryEnd().equals(BigDecimal.ZERO) ? kerja.getSalaryEnd() : request.getSalaryEnd());
        kerja.setWorkingType(request.getWorkingType().isEmpty() ? kerja.getWorkingType() : request.getWorkingType());
        kerja.setPostingDate(request.getPostingDate().isEmpty() ? kerja.getPostingDate() : DateConversion.toDate(request.getPostingDate(), DateFormats.datetime));
        kerja.setExpiredDate(request.getExpiredDate().isEmpty() ? kerja.getExpiredDate() : DateConversion.toDate(request.getExpiredDate(), DateFormats.datetime));
        kerja.setPartnerId(request.getPartnerId().isEmpty() ? kerja.getPartnerId() : request.getPartnerId());
        kerja.setCompanyIcon(request.getCompanyIcon().isEmpty() ? kerja.getCompanyIcon() : request.getCompanyIcon());
        kerja.setCompanyImage(request.getCompanyImage().isEmpty() ? kerja.getCompanyImage() : request.getCompanyImage());
        kerja.setUrl(setupUrlKerja(kerja.getTitle(), kerja.getId()));
        kerja.setCreatedAt(request.getId().isEmpty() ? DateConversion.getDateNow(DateFormats.datetime) : kerja.getCreatedAt());
        kerja.setUpdatedAt(DateConversion.getDateNow(DateFormats.datetime));
        kerja.setStatus(request.getStatus());
        kerja.setViews(DefaultValues.emptyInteger);
        return kerja;
    }

    public String setupUrlKerja(String name, String kerjaId) {
        String url = DefaultValues.emptyString;
        if (!name.isEmpty()) {
            url = name.toLowerCase().replaceAll(RegexCodes.removeSpecialCharacter, RegexCodes.space);
            url = url.replaceAll(RegexCodes.space, RegexCodes.hyphen);
        }
        return String.format(
            FormatValues.url,
            url,
            kerjaId
        ).toLowerCase();
    }

}
