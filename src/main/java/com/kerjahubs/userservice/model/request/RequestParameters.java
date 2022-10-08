package com.kerjahubs.userservice.model.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestParameters {
    List<String> listConfig = new ArrayList<>();
}
