package com.kerjahubs.userservice.service;

import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.userservice.repository.LowonganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LowonganService {
    @Autowired
    LowonganRepository lowonganRepository;


}
