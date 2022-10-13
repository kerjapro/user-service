package com.kerjahubs.userservice.service.user;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.constant.FormatValues;
import com.kerjahubs.common.constant.MessageValues;
import com.kerjahubs.common.enums.AppsLinkedAccount;
import com.kerjahubs.common.enums.DocumentGroupType;
import com.kerjahubs.common.enums.UserType;
import com.kerjahubs.common.model.request.BaseRequest;
import com.kerjahubs.common.model.response.BaseResponse;
import com.kerjahubs.common.utility.DateConversion;
import com.kerjahubs.common.utility.StringConversion;
import com.kerjahubs.userservice.entity.user.UserBase;
import com.kerjahubs.userservice.entity.user.UserDocument;
import com.kerjahubs.userservice.entity.user.UserLinkedAccount;
import com.kerjahubs.userservice.model.dto.user.DocumentDto;
import com.kerjahubs.userservice.model.dto.user.LinkedAccountsDto;
import com.kerjahubs.userservice.model.RetailData;
import com.kerjahubs.userservice.model.request.user.RequestLogin;
import com.kerjahubs.userservice.model.response.ResponseLogin;
import com.kerjahubs.userservice.repository.user.UserBaseRepository;
import com.kerjahubs.userservice.repository.user.UserLinkedAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    UserBaseRepository userBaseRepository;

    @Autowired
    UserLinkedAccountRepository userLinkedAccountRepository;

    public BaseResponse<ResponseLogin> login(BaseRequest<RequestLogin> baseRequest) {
        BaseResponse<ResponseLogin> response = new BaseResponse<>();

        try {
            ResponseLogin dataResponse;
            if (baseRequest.getRequest().getIsUsingOtherAccount()) {
                UserBase userBase = setupUserBase(baseRequest.getRequest());
                UserLinkedAccount userLinkedAccount = setupUserLinkedAccount(
                    baseRequest.getRequest(),
                    userBase
                );
                userLinkedAccountRepository.saveAndFlush(userLinkedAccount);
                dataResponse = setupResponseLogin(userBase);
                response.setResponse(dataResponse);
                return response;
            }

            UserBase userBase = userBaseRepository.findByUsernameAndPassword(
                baseRequest.getRequest().getUsername(),
                baseRequest.getRequest().getPassword()
            ).orElseGet(UserBase::new);

            if (!userBaseRepository.existsByEmail(baseRequest.getRequest().getUsername())
                && !userBaseRepository.existsByPhoneNumber(baseRequest.getRequest().getUsername())) {
                response.setResponseError(
                    MessageValues.error.title.general,
                    MessageValues.error.message.user.login.notFound,
                    new ResponseLogin()
                );
                return response;
            }

            if (!userBase.getPassword().equalsIgnoreCase(baseRequest.getRequest().getPassword())) {
                response.setResponseError(
                    MessageValues.error.title.general,
                    MessageValues.error.message.user.login.wrongPassword,
                    new ResponseLogin()
                );
                return response;
            }

            dataResponse = setupResponseLogin(
                userBase
            );
            response.setResponse(dataResponse);
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general,
                new ResponseLogin()
            );
        }
        return response;
    }

    public UserBase setupUserBase(RequestLogin request){
        UserBase userBase = userBaseRepository.findByEmail(
            request.getOtherAccount().getEmail()
        ).orElseGet(UserBase::new);

        List<UserLinkedAccount> listAccounts = new ArrayList<>();

        if(userBase.getCid().isEmpty()){
            userBase = registerUserBase(request);
            userBaseRepository.save(userBase);

            listAccounts.add(setupUserLinkedAccount(request, userBase));
            userBase.setUserLinkedAccounts(listAccounts);
        }

        return userBase;
    }

    public UserLinkedAccount setupUserLinkedAccount(
        RequestLogin request,
        UserBase userBase
    ) {
        UserLinkedAccount userLinkedAccount = userLinkedAccountRepository.findByAppsAndEmail(
            request.getOtherAccount().getType(),
            request.getOtherAccount().getEmail()
        ).orElseGet(UserLinkedAccount::new);

        if(userLinkedAccount.getId().isEmpty()){
            userLinkedAccount.setId(
                UUID.randomUUID().toString()
            );
        }

        userLinkedAccount.setCid(userBase.getCid());
        userLinkedAccount.setApps(AppsLinkedAccount.valueOf(request.getOtherAccount().getType()));
        userLinkedAccount.setAppsId(request.getOtherAccount().getId());
        userLinkedAccount.setAppsCustomerName(request.getOtherAccount().getName());
        userLinkedAccount.setAppsCustomerImage(request.getOtherAccount().getImage());
        userLinkedAccount.setAppsCustomerEmail(request.getOtherAccount().getEmail());
        userLinkedAccount.setAppsToken(request.getOtherAccount().getToken());
        userLinkedAccount.setGroupType(DocumentGroupType.RETAIL);
        return userLinkedAccount;
    }

    public UserBase registerUserBase(RequestLogin request) {
        int totalUser = (int) (userBaseRepository.count() + 1);
        UserBase userBase = new UserBase();
        userBase.setCid(
            String.format(
                FormatValues.cid,
                DateConversion.toString(new Date(), DateFormats.id),
                StringConversion.leftPad(String.valueOf(totalUser), FormatValues.leftPadCid, FormatValues.padList)
            )
        );
        userBase.setFullName(request.getOtherAccount().getName());
        userBase.setEmail(request.getOtherAccount().getEmail());
        userBase.setType(UserType.RETAIL);
        userBase.setLastLogin(DateConversion.getDateNow(DateFormats.datetime));
        userBase.setSessionId(UUID.randomUUID().toString());
        userBase.setPlatformRegister(request.getPlatform());
        return userBase;
    }

    public ResponseLogin setupResponseLogin(
        UserBase userBase
    ) {
        ResponseLogin responseLogin = new ResponseLogin();
        responseLogin.setCid(userBase.getCid());
        responseLogin.setEmail(userBase.getEmail());
        responseLogin.setPhoneNumber(userBase.getPhoneNumber());
        responseLogin.setType(userBase.getType().toString());
        responseLogin.setIsVerified(userBase.getIsVerified());
        responseLogin.setLastLogin(DateConversion.toString(userBase.getLastLogin(), DateFormats.datetime));
        responseLogin.setRetailData(setupRetailData(userBase));
        responseLogin.setToken(UUID.randomUUID().toString());
        return responseLogin;
    }

    public RetailData setupRetailData(
        UserBase userBase
    ) {
        RetailData retailData = new RetailData();
        retailData.setFullname(userBase.getFullName());
        retailData.setGender(
            userBase.getGender() == null ? DefaultValues.emptyString : userBase.getGender().toString()
        );
        retailData.setBirthDate(DateConversion.toString(userBase.getBirthDate(), DateFormats.birthdate));
        retailData.setPreferences(StringConversion.stringToList(userBase.getPreferences()));
        retailData.setSectors(StringConversion.stringToList(userBase.getSectors()));
        retailData.setDocuments(setupDocuments(userBase.getUserDocuments()));
        retailData.setLinkedAccounts(setupLinkedAccounts(userBase.getUserLinkedAccounts()));
        return retailData;
    }

    public List<DocumentDto> setupDocuments(List<UserDocument> listDocument) {
        List<DocumentDto> documentsResponse = new ArrayList<>();
        List<UserDocument> filterList = listDocument.stream().filter(
            documents -> documents.getGroupType().getCode().equals(UserType.RETAIL.getCode())
        ).collect(Collectors.toList());

        if (filterList.size() > 0) {
            for (UserDocument document : filterList) {
                DocumentDto documents = new DocumentDto();
                documents.setType(document.getDocumentType().getName());
                documents.setValue(document.getValue());
                documentsResponse.add(documents);
            }
        }
        return documentsResponse;
    }

    public List<LinkedAccountsDto> setupLinkedAccounts(List<UserLinkedAccount> listAccounts) {
        List<LinkedAccountsDto> accountsResponse = new ArrayList<>();
        List<UserLinkedAccount> filterList = listAccounts.stream().filter(
            accounts -> accounts.getGroupType().getCode().equals(UserType.RETAIL.getCode())
        ).collect(Collectors.toList());

        if (filterList.size() > 0) {
            for (UserLinkedAccount account : filterList) {
                LinkedAccountsDto accounts = new LinkedAccountsDto();
                accounts.setAppsId(account.getAppsId());
                accounts.setApps(AppsLinkedAccount.valueOf(account.getApps().toString()).getCode());
                accounts.setAppsCustomerName(account.getAppsCustomerName());
                accounts.setAppsCustomerEmail(account.getAppsCustomerEmail());
                accounts.setAppsCustomerImage(account.getAppsCustomerImage());
                accountsResponse.add(accounts);
            }
        }
        return accountsResponse;
    }
}
