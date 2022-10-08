package com.kerjahubs.userservice.service;

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
import com.kerjahubs.userservice.entity.UserBase;
import com.kerjahubs.userservice.entity.UserDocument;
import com.kerjahubs.userservice.entity.UserLinkedAccount;
import com.kerjahubs.userservice.model.Documents;
import com.kerjahubs.userservice.model.RetailData;
import com.kerjahubs.userservice.model.request.RequestLogin;
import com.kerjahubs.userservice.model.response.ResponseLogin;
import com.kerjahubs.userservice.repository.UserBaseRepository;
import com.kerjahubs.userservice.repository.UserLinkedAccountRepository;
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
            ResponseLogin dataResponse = new ResponseLogin();
            String cid = DefaultValues.emptyString;
            if (baseRequest.getRequest().getIsUsingOtherAccount()) {
                UserLinkedAccount userLinkedAccount = setupUserLinkedAccount(
                    baseRequest.getRequest()
                );
                userLinkedAccountRepository.saveAndFlush(userLinkedAccount);
                cid = userLinkedAccount.getCid();
            }

            if (!baseRequest.getRequest().getUsername().isEmpty()
                && !userBaseRepository.existsByEmail(baseRequest.getRequest().getUsername())
                && !userBaseRepository.existsByPhoneNumber(baseRequest.getRequest().getUsername())
            ) {
                response.setResponseError(
                    MessageValues.error.title.general,
                    MessageValues.error.message.login.notFound
                );
                return response;
            }

            if (!baseRequest.getRequest().getPassword().isEmpty()
                && !userBaseRepository.existsByPassword(baseRequest.getRequest().getPassword())
            ) {
                response.setResponseError(
                    MessageValues.error.title.general,
                    MessageValues.error.message.login.wrongPassword
                );
                return response;
            }

            setupResponseLogin(
                baseRequest.getRequest(),
                baseRequest.getRequest().getIsUsingOtherAccount(),
                cid
            );

            response.setResponse(dataResponse);
        } catch (Exception e) {
            response.setResponseError(
                MessageValues.error.title.general,
                MessageValues.error.message.general
            );
        }
        return response;
    }

    public UserLinkedAccount setupUserLinkedAccount(
        RequestLogin request
    ) {
        boolean existUser = userLinkedAccountRepository.existsOtherAccount(
            request.getOtherAccount().getType(),
            request.getOtherAccount().getEmail()
        );

        UserLinkedAccount userLinkedAccount = userLinkedAccountRepository.findByAppsAndEmail(
            request.getOtherAccount().getType(),
            request.getOtherAccount().getEmail()
        ).orElseGet(UserLinkedAccount::new);

        UserBase userBase = userBaseRepository.findById(userLinkedAccount.getCid()).orElseGet(UserBase::new);

        if (!existUser) {
            registerUserBase(request);
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

    public void registerUserBase(RequestLogin request) {
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
        userBaseRepository.save(userBase);
    }

    public void setupResponseLogin(
        RequestLogin request,
        Boolean usingLinkedAccount,
        String cid
    ) {
        ResponseLogin responseLogin = new ResponseLogin();
        UserBase userBase;

        if (usingLinkedAccount) {
            userBase = userBaseRepository.findById(
                cid
            ).orElseGet(UserBase::new);
        } else {
            userBase = userBaseRepository.findByUsernameAndPassword(
                request.getUsername(),
                request.getPassword()
            ).orElseGet(UserBase::new);
        }

        responseLogin.setCid(userBase.getCid());
        responseLogin.setEmail(userBase.getEmail());
        responseLogin.setPhoneNumber(userBase.getPhoneNumber());
        responseLogin.setType(userBase.getType().toString());
        responseLogin.setIsVerified(userBase.getIsVerified());
        responseLogin.setLastLogin(DateConversion.toString(userBase.getLastLogin(), DateFormats.datetime));
        responseLogin.setRetailData(setupRetailData(userBase));
        responseLogin.setSessionId(UUID.randomUUID().toString());
    }

    public RetailData setupRetailData(
        UserBase userBase
    ) {
        RetailData retailData = new RetailData();
        retailData.setFullname(userBase.getFullName());
        retailData.setGender(
            userBase.getGender()==null ? DefaultValues.emptyString : userBase.getGender().toString()
        );
        retailData.setBirthDate(DateConversion.toString(userBase.getBirthDate(), DateFormats.birthdate));
        retailData.setPreferences(StringConversion.stringToList(userBase.getPreferences()));
        retailData.setSectors(StringConversion.stringToList(userBase.getSectors()));
        retailData.setDocuments(setupDocuments(userBase.getUserDocuments()));
        retailData.setLinkedAccounts(new ArrayList<>());
        return retailData;
    }

    public List<Documents> setupDocuments(List<UserDocument> listDocument){
        List<Documents> documentsResponse = new ArrayList<>();
        List<UserDocument> filterList = listDocument.stream().filter(
            documents -> documents.getGroupType().equals(UserType.RETAIL.getCode())
        ).collect(Collectors.toList());

        if(filterList.size()>0){
            for (UserDocument document : filterList) {
                Documents documents = new Documents();
                documents.setType(document.getDocumentType().getName());
                documents.setValue(document.getValue());
                documentsResponse.add(documents);
            }
        }
        return documentsResponse;
    }
}
