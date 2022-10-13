package com.kerjahubs.userservice.service.lowongan;

import com.kerjahubs.userservice.repository.lowongan.LowonganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LowonganService {
    @Autowired
    LowonganRepository lowonganRepository;


}
