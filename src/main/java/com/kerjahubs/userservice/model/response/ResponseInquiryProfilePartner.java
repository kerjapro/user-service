package com.kerjahubs.userservice.model.response;

import com.kerjahubs.userservice.model.PartnerData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseInquiryProfilePartner {
    private List<PartnerData> partnerData = new ArrayList<>();
}
