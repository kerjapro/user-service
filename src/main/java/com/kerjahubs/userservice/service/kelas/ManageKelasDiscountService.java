package com.kerjahubs.userservice.service.kelas;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.DiscountType;
import com.kerjahubs.common.enums.ModulType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.userservice.entity.kelas.KelasDiscount;
import com.kerjahubs.userservice.entity.kelas.KelasModul;
import com.kerjahubs.userservice.model.request.kelas.RequestAddModuleProduct;
import com.kerjahubs.userservice.model.request.kelas.RequestManageDiscountProduct;
import com.kerjahubs.userservice.repository.kelas.KelasDiscountRepository;
import com.kerjahubs.userservice.repository.kelas.KelasModulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ManageKelasDiscountService {
    @Autowired
    KelasDiscountRepository kelasDiscountRepository;

    public BaseResponse<Object> manageKelasDiscount(BaseRequest<RequestManageDiscountProduct> baseRequest){
        BaseResponse<Object> response = new BaseResponse<>();

        try{
            KelasDiscount discount = setupDiscountKelas(baseRequest.getRequest());
            kelasDiscountRepository.saveAndFlush(discount);
            if(!baseRequest.getRequest().getStatus()){
                response.setResponseSuccess(
                    MessageValues.success.title.kelas.discount.delete,
                    MessageValues.success.message.kelas.discount.delete
                );
            }else{
                if(baseRequest.getRequest().getId().isEmpty()){
                    response.setResponseSuccess(
                        MessageValues.success.title.kelas.discount.add,
                        MessageValues.success.message.kelas.discount.add
                    );
                }else{
                    response.setResponseSuccess(
                        MessageValues.success.title.kelas.discount.edit,
                        MessageValues.success.message.kelas.discount.edit
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

    private KelasDiscount setupDiscountKelas(RequestManageDiscountProduct request) {
        KelasDiscount discount = kelasDiscountRepository.findByKelasId(request.getProductId()).orElseGet(KelasDiscount::new);
        discount.setId(request.getId().isEmpty() ? UUID.randomUUID().toString() : request.getId());
        discount.setKelasId(request.getProductId().isEmpty() ? discount.getKelasId() : request.getProductId());
        discount.setDiscountType(request.getDiscountType().isEmpty() ? discount.getDiscountType(): DiscountType.valueOf(request.getDiscountType()));
        discount.setDiscountValue(request.getDiscountValue().equals(BigDecimal.ZERO) ? discount.getDiscountValue() : request.getDiscountValue());
        discount.setDiscountName(request.getDiscountName().isEmpty() ? discount.getDiscountName() : request.getDiscountName());
        discount.setDiscountDesc(request.getDiscountDesc().isEmpty() ? discount.getDiscountDesc() : request.getDiscountDesc());
        discount.setStartDate(request.getStartDate().isEmpty() ? discount.getCreatedAt() : DateConversion.toDate(request.getStartDate(), DateFormats.datetime));
        discount.setExpiredDate(request.getExpiredDate().isEmpty() ? discount.getExpiredDate() : DateConversion.toDate(request.getExpiredDate(), DateFormats.datetime));
        discount.setIsHaveQuota(request.getIsHaveQuota());
        discount.setQuota(request.getQuota()==DefaultValues.emptyInteger ? discount.getQuota() : request.getQuota());
        discount.setStatus(request.getStatus());
        discount.setCreatedAt(request.getId().isEmpty() ? DateConversion.getDateNow(DateFormats.datetime) : discount.getCreatedAt());
        discount.setUpdatedAt(DateConversion.getDateNow(DateFormats.datetime));
        return discount;
    }
}
