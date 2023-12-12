package com.danit.springrest.dto;

import com.danit.springrest.model.Account;
import com.danit.springrest.model.Customer;
import com.danit.springrest.model.Employee;
import com.danit.springrest.model.Employer;
import com.danit.springrest.service.AccountService;
import com.danit.springrest.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankFacade {

    private final ModelMapper modelMapper;
    private final CustomerService customerService;
    private final AccountService accountService;


    public BankFacade(ModelMapper modelMapper, CustomerService customerService, AccountService accountService) {
        this.modelMapper = modelMapper;
        this.customerService = customerService;
        this.accountService = accountService;
    }


    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequest) {
        Customer customer = modelMapper.map(customerRequest, Customer.class);
        Customer savedCustomer = customerService.createCustomer(customer);
        return modelMapper.map(savedCustomer, CustomerResponseDTO.class);
    }

    public AccountResponseDTO createAccount(AccountRequestDTO accountRequest) {
        Account account = modelMapper.map(accountRequest, Account.class);
        Account savedAccount = accountService.createAccount(account);
        return modelMapper.map(savedAccount, AccountResponseDTO.class);
    }
}
