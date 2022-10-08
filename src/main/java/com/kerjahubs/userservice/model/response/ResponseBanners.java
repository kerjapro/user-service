package com.kerjahubs.userservice.model.response;

import com.kerjahubs.userservice.model.dto.BannersDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseBanners {
    private List<BannersDto> banners = new ArrayList<>();
}
