package com.skillstorm.project1.mappers;

import org.springframework.stereotype.Component;

import com.skillstorm.project1.dtos.AccountDto;
import com.skillstorm.project1.models.Account;

@Component
public class AccountMapper {

    public Account toAccount(AccountDto dto) {
        return new Account(dto.getId(), dto.getUsername(), dto.getCompany());
    }

    public AccountDto toDto(Account acct) {
        return new AccountDto(acct.getId(), acct.getUsername(), acct.getCompany());
    }
    
}
