package com.kerjahubs.userservice.model.response;

import com.kerjahubs.userservice.entity.Preference;
import com.kerjahubs.userservice.model.dto.BannersDto;
import com.kerjahubs.userservice.model.dto.SectorsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseParameters {
    List<Preference> preferences = new ArrayList<>();
    List<SectorsDto> sectors = new ArrayList<>();
    List<BannersDto> banners = new ArrayList<>();
}
