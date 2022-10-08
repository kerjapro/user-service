package com.kerjahubs.userservice.service;

import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.constant.ParameterValues;
import com.kerjahubs.userservice.entity.SectorSubs;
import com.kerjahubs.userservice.model.dto.BannersDto;
import com.kerjahubs.userservice.model.dto.SectorSubDto;
import com.kerjahubs.userservice.model.dto.SectorsDto;
import com.kerjahubs.userservice.model.request.RequestParameters;
import com.kerjahubs.userservice.model.response.ResponseParameters;
import com.kerjahubs.userservice.repository.BannersRepository;
import com.kerjahubs.userservice.repository.PreferenceRepository;
import com.kerjahubs.userservice.repository.SectorSubsRepository;
import com.kerjahubs.userservice.repository.SectorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParametersService {

    @Autowired
    PreferenceRepository preferenceRepository;

    @Autowired
    SectorsRepository sectorsRepository;

    @Autowired
    SectorSubsRepository sectorSubsRepository;

    @Autowired
    BannersRepository bannersRepository;

    public BaseResponse<ResponseParameters> getParameters(BaseRequest<RequestParameters> baseRequest) {
        BaseResponse<ResponseParameters> response = new BaseResponse<>();

        try {
            ResponseParameters responseParameters = new ResponseParameters();
            if (!baseRequest.getRequest().getListConfig().isEmpty()) {
                if (baseRequest.getRequest().getListConfig().contains(ParameterValues.preferences)) {
                    responseParameters.setPreferences(
                        preferenceRepository.findAll()
                    );
                }

                if (baseRequest.getRequest().getListConfig().contains(ParameterValues.sectors)) {
                    responseParameters.setSectors(
                        new SectorsDto().convert(
                            sectorsRepository.findAll()
                        )
                    );

                    List<SectorSubs> listSectorSub = new ArrayList<>(sectorSubsRepository.findAll());

                    int i = 0;
                    for (SectorsDto sector : responseParameters.getSectors()) {
                        List<SectorSubDto> listSectorSubFilter = new SectorSubDto().convert(
                            listSectorSub.stream().filter(
                                sectorSubs -> sectorSubs.getSectorGroup().equalsIgnoreCase(sector.getId())
                            ).collect(Collectors.toList())
                        );
                        responseParameters.getSectors().get(i).setSubSectors(listSectorSubFilter);
                        i++;
                    }
                }

                if (baseRequest.getRequest().getListConfig().contains(ParameterValues.banners)) {
                    responseParameters.setBanners(new BannersDto().convert(
                        bannersRepository.findAll()
                    ));
                }
            }

            response.setResponse(responseParameters);
        } catch (Exception e) {
            response.setResponseError();
        }
        return response;
    }
}
