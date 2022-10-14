package com.kerjahubs.userservice.model.dto.kelas;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.kelas.KelasBenefit;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class KelasBenefitDto implements DtoConverter<KelasBenefit, KelasBenefitDto> {
    private String id = DefaultValues.emptyString;
    private String benefitName = DefaultValues.emptyString;
    private Integer sequence = DefaultValues.emptyInteger;
    private String status = DefaultValues.emptyString;

    public KelasBenefitDto(KelasBenefit benefit) {
        this.id = benefit.getId();
        this.benefitName = benefit.getBenefitName();
        this.sequence = benefit.getSequence();
        this.status = benefit.getStatus().toString();
    }

    @Override
    public KelasBenefitDto convert(KelasBenefit kelasBenefit) {
        return new KelasBenefitDto(kelasBenefit);
    }

    @Override
    public List<KelasBenefitDto> convert(List<KelasBenefit> kelasBenefits) {
        return DtoConverter.super.convert(kelasBenefits);
    }
}
