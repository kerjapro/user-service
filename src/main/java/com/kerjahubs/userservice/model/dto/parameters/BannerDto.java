package com.kerjahubs.userservice.model.dto.parameters;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.parameter.Banners;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BannerDto implements DtoConverter<Banners, BannerDto> {
    public String image = DefaultValues.emptyString;
    public String url = DefaultValues.emptyString;
    public int sequence = DefaultValues.emptyInteger;

    public BannerDto(Banners banners){
        this.image = banners.getImage();
        this.url = banners.getUrl();
        this.sequence = banners.getSequence();
    }

    @Override
    public BannerDto convert(Banners banners) {
        return new BannerDto(banners);
    }

    @Override
    public List<BannerDto> convert(List<Banners> banners) {
        return DtoConverter.super.convert(banners);
    }
}
