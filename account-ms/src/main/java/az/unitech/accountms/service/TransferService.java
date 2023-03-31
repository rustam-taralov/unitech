package az.unitech.accountms.service;

import az.unitech.accountms.dtos.request.AccountToAccountRequest;
import az.unitech.accountms.enums.AccountStatus;
import az.unitech.accountms.exception.BusinessStatus;
import az.unitech.accountms.exception.ExceptionHelper;
import az.unitech.accountms.model.UserAccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final ExceptionHelper exceptionHelper;
    private final UserAccountService accountService;

    @Transactional(isolation = REPEATABLE_READ)
    public void transferMoney(AccountToAccountRequest accountToAccountRequest){

        if(accountToAccountRequest.getAccountTo().equals(accountToAccountRequest.getAccountFrom()))
            exceptionHelper.throwException(BusinessStatus.ACCOUNT_SAME);

        var fromAccount= accountService.getAccountEntityByAccountNumber(accountToAccountRequest.getAccountFrom());

        checkAccount(fromAccount);
        if(accountToAccountRequest.getAmount().compareTo(fromAccount.getBalance())<0)
            exceptionHelper.throwException(BusinessStatus.NOT_ENOUGH_BALANCE);

        fromAccount.setBalance(fromAccount.getBalance().subtract(accountToAccountRequest.getAmount()));
        accountService.updateAccount(fromAccount);

        var toAccount= accountService.getAccountEntityByAccountNumber(accountToAccountRequest.getAccountTo());
        checkAccount(toAccount);

        fromAccount.setBalance(fromAccount.getBalance().add(accountToAccountRequest.getAmount()));
        accountService.updateAccount(toAccount);
    }

    private void checkAccount(UserAccountEntity accountEntity){
        if(AccountStatus.BLOCKED == accountEntity.getStatus())
            exceptionHelper.throwException(BusinessStatus.ACCOUNT_BLOCKED);
    }
}
