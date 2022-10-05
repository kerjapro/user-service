package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.FormatValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.DocumentGroupType;
import com.kerjahubs.common.enums.PartnerType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.common.utility.StringConversion;
import com.kerjahubs.userservice.entity.UserDocument;
import com.kerjahubs.userservice.entity.UserPartner;
import com.kerjahubs.userservice.model.Documents;
import com.kerjahubs.userservice.model.request.RequestRegisterPartner;
import com.kerjahubs.userservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RegisterPartnerService {
    @Autowired
    UserPartnerRepository userPartnerRepository;

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Autowired
    UserDocumentRepository userDocumentRepository;

    public BaseResponse<Object> registerPartner(BaseRequest<RequestRegisterPartner> baseRequest) {
        BaseResponse<Object> response = new BaseResponse<>();

        try {
            if (!baseRequest.getRequest().getPartnerName().isEmpty()
                && userPartnerRepository.existsByPartnerName(baseRequest.getRequest().getPartnerName())
            ) {
                response.setResponseError(
                    MessageValues.error.title.register.general,
                    MessageValues.error.message.register.partner.existName
                );
                return response;
            }

            UserPartner userPartner = setupUserPartner(baseRequest.getRequest());
            if (baseRequest.getRequest().getDocuments().size() > 0) {
                List<UserDocument> listDocument = setupDocumentUserPartner(baseRequest.getRequest(), userPartner);
                userDocumentRepository.saveAll(listDocument);
            }
            userPartnerRepository.save(userPartner);
            response.setResponseSuccess(
                MessageValues.success.title.register.general,
                MessageValues.success.message.register.partner
            );
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general
            );
        }
        return response;
    }

    public UserPartner setupUserPartner(RequestRegisterPartner request) {
        int runningPartner = userPartnerRepository.runningNumberPartner(request.getPartnerType())+1;

        UserPartner userPartner = new UserPartner();
        userPartner.setCid(request.getCid());
        userPartner.setPartnerId(
            request.getPartnerType().equalsIgnoreCase(PartnerType.CORPORATE.getCode())
                ? String.format(
                FormatValues.partnerCorporate,
                DateConversion.toString(new Date(), DateFormats.id),
                StringConversion.leftPad(String.valueOf(runningPartner), FormatValues.leftPadCorporate, FormatValues.padList)
            )
                : String.format(
                FormatValues.partnerTrainer,
                DateConversion.toString(new Date(), DateFormats.id),
                StringConversion.leftPad(String.valueOf(runningPartner), FormatValues.leftPadTrainer, FormatValues.padList)
            )
        );
        userPartner.setPartnerType(PartnerType.valueOf(request.getPartnerType()));
        userPartner.setPartnerName(request.getPartnerName());
        userPartner.setAbout(request.getAbout());
        userPartner.setTagline(request.getTagline());
        userPartner.setWebsite(request.getWebsite());
        userPartner.setProvinceId(request.getProvinceId());
        userPartner.setProvinceName(request.getProvinceName());
        userPartner.setDistrictId(request.getDistrictId());
        userPartner.setDistrictName(request.getDistrictName());
        userPartner.setAddress(request.getAddress());
        userPartner.setPostalCode(request.getPostalCode());
        userPartner.setMaps(request.getMaps());
        userPartner.setNik(request.getNik());
        userPartner.setNpwpNumber(request.getNpwpNumber());
        return userPartner;
    }

    public List<UserDocument> setupDocumentUserPartner(RequestRegisterPartner request, UserPartner userPartner) {
        List<UserDocument> listDocument = new ArrayList<>();
        for (Documents document : request.getDocuments()) {
            UserDocument userDocument = new UserDocument();
            userDocument.setId(UUID.randomUUID().toString());
            userDocument.setCid(userPartner.getCid());
            userDocument.setTypeId(documentTypeRepository.findIdByName(document.getType()));
            userDocument.setGroupType(DocumentGroupType.valueOf(request.getPartnerType()));
            userDocument.setValue(document.getValue());
            listDocument.add(userDocument);
        }
        return listDocument;
    }
}
