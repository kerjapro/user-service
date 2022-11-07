package com.kerjahubs.userservice.service.user;

import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.AppsLinkedAccount;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.entity.user.UserBase;
import com.kerjahubs.userservice.entity.user.UserDocument;
import com.kerjahubs.userservice.entity.user.UserLinkedAccount;
import com.kerjahubs.userservice.entity.user.UserPartner;
import com.kerjahubs.userservice.model.dto.user.DocumentDto;
import com.kerjahubs.userservice.model.dto.user.LinkedAccountsDto;
import com.kerjahubs.userservice.model.PartnerData;
import com.kerjahubs.userservice.model.request.user.RequestInquiryProfile;
import com.kerjahubs.userservice.model.response.ResponseInquiryProfilePartner;
import com.kerjahubs.userservice.repository.user.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryProfilePartnerService {
    @Autowired
    UserBaseRepository userBaseRepository;

    public BaseResponse<ResponseInquiryProfilePartner> getProfilePartner(BaseRequest<RequestInquiryProfile> baseRequest) {
        BaseResponse<ResponseInquiryProfilePartner> response = new BaseResponse<>();

        try {
            UserBase userBase = userBaseRepository.findById(baseRequest.getRequest().getCid()).orElseGet(UserBase::new);
            ResponseInquiryProfilePartner responseInquiryProfilePartner = setupResponse(userBase);
            response.setResponse(responseInquiryProfilePartner);
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                e.getMessage(),
                new ResponseInquiryProfilePartner()
            );
        }
        return response;
    }

    public ResponseInquiryProfilePartner setupResponse(UserBase userBase) {
        ResponseInquiryProfilePartner responseInquiryProfilePartner = new ResponseInquiryProfilePartner();
        List<PartnerData> listPartner = new ArrayList<>();

        List<UserPartner> partners = userBase.getUserPartners();
        if (partners.size() > 0) {
            for (UserPartner partner : partners) {
                PartnerData partnerData = new PartnerData();
                partnerData.setPartnerId(partner.getPartnerId());
                partnerData.setPartnerName(partner.getPartnerName());
                partnerData.setPartnerType(partner.getPartnerType() != null ? partner.getPartnerType().toString() : "");
                partnerData.setAbout(partner.getAbout());
                partnerData.setTagline(partner.getTagline());
                partnerData.setWebsite(partner.getWebsite());
                partnerData.setProvinceId(partner.getProvinceId());
                partnerData.setProvinceName(partner.getProvinceName());
                partnerData.setDistrictId(partner.getDistrictId());
                partnerData.setDistrictName(partner.getDistrictName());
                partnerData.setAddress(partner.getAddress());
                partnerData.setPostalCode(partner.getPostalCode());
                partnerData.setMaps(partner.getMaps());
                partnerData.setDocuments(setupDocuments(userBase.getUserDocuments(), partnerData.getPartnerType()));
                partnerData.setLinkedAccounts(setupLinkedAccounts(userBase.getUserLinkedAccounts(), partnerData.getPartnerType()));
                listPartner.add(partnerData);
            }
        }
        responseInquiryProfilePartner.setPartnerData(listPartner);
        return responseInquiryProfilePartner;
    }

    public List<DocumentDto> setupDocuments(List<UserDocument> listDocument, String type) {
        List<DocumentDto> documentsResponse = new ArrayList<>();
        List<UserDocument> filterList = listDocument.stream().filter(
            documents -> documents.getGroupType().getCode().equals(type)
        ).collect(Collectors.toList());

        if (filterList.size() > 0) {
            for (UserDocument document : filterList) {
                DocumentDto documents = new DocumentDto();
                documents.setType(document.getDocumentType().getName());
                documents.setValue(document.getValue());
                documentsResponse.add(documents);
            }
        }
        return documentsResponse;
    }

    public List<LinkedAccountsDto> setupLinkedAccounts(List<UserLinkedAccount> listAccounts, String type) {
        List<LinkedAccountsDto> accountsResponse = new ArrayList<>();
        List<UserLinkedAccount> filterList = listAccounts.stream().filter(
            accounts -> accounts.getGroupType().getCode().equals(type)
        ).collect(Collectors.toList());

        if (filterList.size() > 0) {
            for (UserLinkedAccount account : filterList) {
                LinkedAccountsDto accounts = new LinkedAccountsDto();
                accounts.setAppsId(account.getAppsId());
                accounts.setApps(AppsLinkedAccount.valueOf(account.getApps().toString()).getCode());
                accounts.setAppsCustomerName(account.getAppsCustomerName());
                accounts.setAppsCustomerEmail(account.getAppsCustomerEmail());
                accounts.setAppsCustomerImage(account.getAppsCustomerImage());
                accountsResponse.add(accounts);
            }
        }
        return accountsResponse;
    }
}
