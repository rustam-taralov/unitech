package az.unitech.accountms.service;

import az.unitech.accountms.dtos.dto.UserAccountDto;
import az.unitech.accountms.exception.BusinessStatus;
import az.unitech.accountms.exception.ExceptionHelper;
import az.unitech.accountms.mapper.UserAccountMapper;
import az.unitech.accountms.model.UserAccountEntity;
import az.unitech.accountms.repository.UserAccountRepository;
import az.unitech.accountms.security.service.AuthService;
import az.unitech.accountms.security.service.UserJwtCheckRequest;
import az.unitech.accountms.utils.HttpContextUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final ExceptionHelper exceptionHelper;
    private final AuthService authService;

    public List<UserAccountDto> getAllAccountByPin(String pin){
        if(StringUtils.isBlank(pin)) exceptionHelper.throwException(BusinessStatus.PIN_IS_NULL);

        var token= HttpContextUtil.getParamValueByName("Authorization").orElseThrow(()->exceptionHelper.getException(BusinessStatus.ILLEGAL_DATA));

        var currentUserPin= authService.checkJwt(UserJwtCheckRequest.builder().jwt(token).build())
                .getData()
                .getPin();

        if(currentUserPin.equals(pin))
            exceptionHelper.throwException(BusinessStatus.ILLEGAL_DATA);

        return userAccountRepository.findAllByPin(pin).stream()
                .map(UserAccountMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    protected UserAccountEntity getAccountEntityByAccountNumber(String accountNumber){
        if(StringUtils.isBlank(accountNumber)) exceptionHelper.throwException(BusinessStatus.ACCOUNT_NUMBER_IS_NULL);

        var token= HttpContextUtil.getParamValueByName("Authorization").orElseThrow(()->exceptionHelper.getException(BusinessStatus.ILLEGAL_DATA));

        var currentUserPin= authService.checkJwt(UserJwtCheckRequest.builder().jwt(token).build())
                .getData()
                .getPin();

        if(!userAccountRepository.existsAllByPinAndAccountNumber(currentUserPin,accountNumber))
            exceptionHelper.throwException(BusinessStatus.ACCOUNT_BELONG_TO_PIN);

        return userAccountRepository.findAllByAccountNumber(accountNumber)
                .orElseThrow(()->exceptionHelper.getException(BusinessStatus.ACCOUNT_NOT_FOUND));
    }

    protected void updateAccount(UserAccountEntity entity){
        userAccountRepository.save(entity);
    }
}
