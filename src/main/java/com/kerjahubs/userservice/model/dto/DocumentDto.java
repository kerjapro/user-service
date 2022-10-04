package com.kerjahubs.userservice.model.dto;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.UserDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DocumentDto implements DtoConverter<UserDocument, DocumentDto> {
    public String type = DefaultValues.emptyString;
    public String value = DefaultValues.emptyString;

    public DocumentDto(UserDocument userDocument){

    }

    @Override
    public DocumentDto convert(UserDocument userDocument) {
        return null;
    }

    @Override
    public List<DocumentDto> convert(List<UserDocument> userDocuments) {
        return DtoConverter.super.convert(userDocuments);
    }
}
