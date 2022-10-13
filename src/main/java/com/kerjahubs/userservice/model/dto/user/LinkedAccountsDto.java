package com.kerjahubs.userservice.model.dto.user;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.DtoConverter;
import com.kerjahubs.userservice.entity.user.UserLinkedAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LinkedAccountsDto implements DtoConverter<UserLinkedAccount, LinkedAccountsDto> {
    private String apps = DefaultValues.emptyString;
    private String appsId = DefaultValues.emptyString;
    private String appsCustomerName = DefaultValues.emptyString;
    private String appsCustomerImage = DefaultValues.emptyString;
    private String appsCustomerEmail = DefaultValues.emptyString;

    public LinkedAccountsDto(UserLinkedAccount userLinkedAccount){
        this.apps = userLinkedAccount.getApps().getCode();
        this.appsId = userLinkedAccount.getAppsId();
        this.appsCustomerName = userLinkedAccount.getAppsCustomerName();
        this.appsCustomerEmail = userLinkedAccount.getAppsCustomerEmail();
        this.appsCustomerImage = userLinkedAccount.getAppsCustomerImage();
    }

    @Override
    public LinkedAccountsDto convert(UserLinkedAccount userLinkedAccount) {
        return new LinkedAccountsDto(userLinkedAccount);
    }

    @Override
    public List<LinkedAccountsDto> convert(List<UserLinkedAccount> userLinkedAccounts) {
        return DtoConverter.super.convert(userLinkedAccounts);
    }
}
