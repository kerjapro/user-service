package com.kerjahubs.userservice.model.dto.user;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.user.UserDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DocumentDto implements DtoConverter<UserDocument, DocumentDto> {
    public String id = DefaultValues.emptyString;
    public String type = DefaultValues.emptyString;
    public String value = DefaultValues.emptyString;

    public DocumentDto(UserDocument userDocument) {
        this.id = userDocument.getId();
        this.type = userDocument.getDocumentType().getName();
        this.value = userDocument.getValue();
    }

    @Override
    public DocumentDto convert(UserDocument userDocument) {
        return new DocumentDto(userDocument);
    }

    @Override
    public List<DocumentDto> convert(List<UserDocument> userDocuments) {
        return DtoConverter.super.convert(userDocuments);
    }
}
