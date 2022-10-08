package com.kerjahubs.userservice.service;

import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.model.dto.BannersDto;
import com.kerjahubs.userservice.model.response.ResponseBanners;
import com.kerjahubs.userservice.repository.BannersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannersService {

    @Autowired
    BannersRepository bannersRepository;

    public BaseResponse<ResponseBanners> getBanners(BaseRequest<Object> baseRequest){
        BaseResponse<ResponseBanners> response = new BaseResponse<>();
        try{
            ResponseBanners responseBanners = new ResponseBanners();
            responseBanners.setBanners(new BannersDto().convert(
                bannersRepository.findAll()
            ));
            response.setResponse(responseBanners);
        }catch (Exception e){
            response.setResponseError();
        }
        return response;
    }

}
