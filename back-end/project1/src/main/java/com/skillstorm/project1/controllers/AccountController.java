package com.skillstorm.project1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.Exceptions.InvalidCredentialsException;
import com.skillstorm.project1.Exceptions.ResourceNotFoundException;
import com.skillstorm.project1.dtos.AccountDto;
import com.skillstorm.project1.models.Account;
import com.skillstorm.project1.repositories.AccountRepository;
import com.skillstorm.project1.services.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
@Tag(name = "Account Controller Endpoints")
public class AccountController {
    
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;


    @GetMapping
    @Operation(summary = "Retrieve all accounts")
    public ResponseEntity<List<AccountDto>> findAllAccounts() {
        List<AccountDto> accounts = accountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Retrieve an account with a given id")
    public ResponseEntity<Object> findAccount(@PathVariable int id) {

        try {
            AccountDto foundAccount = accountService.findAccountById(id);
            return new ResponseEntity<>(foundAccount, HttpStatus.OK);

        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/auth")
    @Operation(summary = "Authenticate a user")
    public ResponseEntity<Object> authenticate(@RequestBody Account acct) {

        try {
            AccountDto dto = accountService.authenticate(acct);
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);

        } catch (InvalidCredentialsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
