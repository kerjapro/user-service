package com.kerjahubs.userservice.model.dto.parameters;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.parameter.Sectors;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SectorDto implements DtoConverter<Sectors, SectorDto> {
    private String id = DefaultValues.emptyString;
    private String group = DefaultValues.emptyString;
    private String value = DefaultValues.emptyString;
    private String url = DefaultValues.emptyString;
    private List<SectorSubDto> subSectors = new ArrayList<>();

    public SectorDto(Sectors sectors) {
        this.id = sectors.getId();
        this.group = sectors.getGroupType();
        this.value = sectors.getUrl();
        this.url = sectors.getValue();
    }

    @Override
    public SectorDto convert(Sectors sectors) {
        return new SectorDto(sectors);
    }

    @Override
    public List<SectorDto> convert(List<Sectors> sectors) {
        return DtoConverter.super.convert(sectors);
    }
}
