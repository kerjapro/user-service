package com.kerjahubs.userservice.model.dto.kelas;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.kelas.KelasModul;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class KelasModulDto implements DtoConverter<KelasModul, KelasModulDto> {
    private String id = DefaultValues.emptyString;
    private String modulType = DefaultValues.emptyString;
    private String modulName = DefaultValues.emptyString;
    private String modulDesc = DefaultValues.emptyString;
    private String file = DefaultValues.emptyString;
    private Integer sequence = DefaultValues.emptyInteger;
    private String status = DefaultValues.emptyString;

    public KelasModulDto(KelasModul modul){
        this.id = modul.getId();
        this.modulType = modul.getModulType().toString();
        this.modulName = modul.getModulName();
        this.modulDesc = modul.getModulDesc();
        this.file = modul.getFile();
        this.sequence = modul.getSequence();
        this.status = modul.getStatus().toString();
    }

    @Override
    public KelasModulDto convert(KelasModul kelasModul) {
        return new KelasModulDto(kelasModul);
    }

    @Override
    public List<KelasModulDto> convert(List<KelasModul> kelasModuls) {
        return DtoConverter.super.convert(kelasModuls);
    }
}
