package com.kerjahubs.userservice.model.dto;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.Sectors;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SectorsDto implements DtoConverter<Sectors, SectorsDto> {
    private String id = DefaultValues.emptyString;
    private String group = DefaultValues.emptyString;
    private String value = DefaultValues.emptyString;
    private String url = DefaultValues.emptyString;
    private List<SectorSubDto> subSectors = new ArrayList<>();

    public SectorsDto(Sectors sectors){
        this.id = sectors.getId();
        this.group = sectors.getGroupType();
        this.value = sectors.getUrl();
        this.url = sectors.getValue();
    }

    @Override
    public SectorsDto convert(Sectors sectors) {
        return new SectorsDto(sectors);
    }

    @Override
    public List<SectorsDto> convert(List<Sectors> sectors) {
        return DtoConverter.super.convert(sectors);
    }
}
