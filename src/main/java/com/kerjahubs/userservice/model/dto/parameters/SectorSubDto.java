package com.kerjahubs.userservice.model.dto.parameters;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.parameter.SectorSubs;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SectorSubDto implements DtoConverter<SectorSubs,SectorSubDto> {
    private String id = DefaultValues.emptyString;
    private String url = DefaultValues.emptyString;
    private String value = DefaultValues.emptyString;

    public SectorSubDto(SectorSubs sectorSubs){
        this.id = sectorSubs.getId();
        this.url = sectorSubs.getUrl();
        this.value = sectorSubs.getValue();
    }

    @Override
    public SectorSubDto convert(SectorSubs sectorSubs) {
        return new SectorSubDto(sectorSubs);
    }

    @Override
    public List<SectorSubDto> convert(List<SectorSubs> sectorSubs) {
        return DtoConverter.super.convert(sectorSubs);
    }
}
