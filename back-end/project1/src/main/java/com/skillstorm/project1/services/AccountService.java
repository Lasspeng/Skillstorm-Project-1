package com.skillstorm.project1.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.Exceptions.InvalidCredentialsException;
import com.skillstorm.project1.Exceptions.ResourceNotFoundException;
import com.skillstorm.project1.dtos.AccountDto;
import com.skillstorm.project1.mappers.AccountMapper;
import com.skillstorm.project1.models.Account;
import com.skillstorm.project1.repositories.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    AccountMapper mapper;

    public List<AccountDto> findAll() {
        List<AccountDto> dtos = accountRepo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());

        return dtos;
    }

    public AccountDto authenticate(Account acct) throws InvalidCredentialsException {
        
        Optional<Account> foundAcct = accountRepo.findByUsernameAndPassword(acct.getUsername(), acct.getPassword());

        if (foundAcct.isPresent()) {
            AccountDto dto = mapper.toDto(foundAcct.get());
            return dto;
        } else {
            throw new InvalidCredentialsException();
        }
    }

    public AccountDto findAccountById(int id) throws ResourceNotFoundException {
        Optional<Account> acct = accountRepo.findById(id);
        AccountDto acctDto = mapper.toDto(acct.get());

        if (acct.isPresent()) {
            return acctDto;
        } else {
            throw new ResourceNotFoundException("account");
        }
    }
}
