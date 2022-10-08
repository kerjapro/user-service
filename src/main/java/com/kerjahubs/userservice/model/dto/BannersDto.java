package com.kerjahubs.userservice.model.dto;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.Banners;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BannersDto implements DtoConverter<Banners,BannersDto> {
    public String image = DefaultValues.emptyString;
    public String url = DefaultValues.emptyString;
    public int sequence = DefaultValues.emptyInteger;

    public BannersDto(Banners banners){
        this.image = banners.getImage();
        this.url = banners.getUrl();
        this.sequence = banners.getSequence();
    }

    @Override
    public BannersDto convert(Banners banners) {
        return new BannersDto(banners);
    }

    @Override
    public List<BannersDto> convert(List<Banners> banners) {
        return DtoConverter.super.convert(banners);
    }
}
