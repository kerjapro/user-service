package com.kerjahubs.userservice.model.dto.kelas;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.kelas.Kelas;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class KelasDto implements DtoConverter<Kelas, KelasDto> {
    public String id = DefaultValues.emptyString;
    public String productType = DefaultValues.emptyString;
    public String partnerName = DefaultValues.emptyString;
    public String partnerImage = DefaultValues.emptyString;
    public String productImage = DefaultValues.emptyString;
    public String productName = DefaultValues.emptyString;
    public BigDecimal price = BigDecimal.ZERO;
    public BigDecimal rating = BigDecimal.ZERO;
    public Integer totalRating = DefaultValues.emptyInteger;
    public Integer views = DefaultValues.emptyInteger;
    public String url = DefaultValues.emptyString;
    public boolean isHaveCertificate = Boolean.TRUE;
    public String sectorName = DefaultValues.emptyString;

    public KelasDto(Kelas kelas){
        this.id = kelas.getId();
        this.productType = kelas.getKelasType().getCode();
        this.productName = kelas.getKelasName();
        this.productImage = kelas.getImage();
        this.price = kelas.getPrice();
        this.url = kelas.getUrl();
    }

    @Override
    public KelasDto convert(Kelas kelas) {
        return null;
    }

    @Override
    public List<KelasDto> convert(List<Kelas> kelas) {
        return DtoConverter.super.convert(kelas);
    }
}
