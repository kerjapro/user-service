package com.kerjahubs.userservice.service;

import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.AppsLinkedAccount;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.entity.*;
import com.kerjahubs.userservice.model.Documents;
import com.kerjahubs.userservice.model.LinkedAccounts;
import com.kerjahubs.userservice.model.PartnerData;
import com.kerjahubs.userservice.model.request.RequestInquiryProfilePartner;
import com.kerjahubs.userservice.model.response.ResponseInquiryProfilePartner;
import com.kerjahubs.userservice.repository.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryProfilePartnerService {
    @Autowired
    UserBaseRepository userBaseRepository;

    public BaseResponse<ResponseInquiryProfilePartner> getProfilePartner(BaseRequest<RequestInquiryProfilePartner> baseRequest){
        BaseResponse<ResponseInquiryProfilePartner> response = new BaseResponse<>();

        try{
            UserBase userBase = userBaseRepository.findById(baseRequest.getRequest().getCid()).orElseGet(UserBase::new);
            ResponseInquiryProfilePartner responseInquiryProfilePartner = setupResponse(userBase);
            response.setResponse(responseInquiryProfilePartner);
        } catch (Exception e){
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general
            );
        }
        return response;
    }

    public ResponseInquiryProfilePartner setupResponse(UserBase userBase){
        ResponseInquiryProfilePartner responseInquiryProfilePartner = new ResponseInquiryProfilePartner();
        List<PartnerData> listPartner = new ArrayList<>();

        List<UserPartner> partners = userBase.getUserPartners();
        if(partners.size()>0){
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

    public List<Documents> setupDocuments(List<UserDocument> listDocument, String type) {
        List<Documents> documentsResponse = new ArrayList<>();
        List<UserDocument> filterList = listDocument.stream().filter(
            documents -> documents.getGroupType().getCode().equals(type)
        ).collect(Collectors.toList());

        if (filterList.size() > 0) {
            for (UserDocument document : filterList) {
                Documents documents = new Documents();
                documents.setType(document.getDocumentType().getName());
                documents.setValue(document.getValue());
                documentsResponse.add(documents);
            }
        }
        return documentsResponse;
    }

    public List<LinkedAccounts> setupLinkedAccounts(List<UserLinkedAccount> listAccounts, String type) {
        List<LinkedAccounts> accountsResponse = new ArrayList<>();
        List<UserLinkedAccount> filterList = listAccounts.stream().filter(
            accounts -> accounts.getGroupType().getCode().equals(type)
        ).collect(Collectors.toList());

        if (filterList.size() > 0) {
            for (UserLinkedAccount account : filterList) {
                LinkedAccounts accounts = new LinkedAccounts();
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