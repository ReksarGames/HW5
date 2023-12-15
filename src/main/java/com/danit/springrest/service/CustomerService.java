package com.danit.springrest.service;

import com.danit.springrest.enums.Currency;
import com.danit.springrest.model.Account;
import com.danit.springrest.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Page<Customer> getAllCustomers(PageRequest pageRequest);
    Customer getCustomerById(Long customerId);
    Customer addCustomer(Customer customer);
    Customer createCustomer(String name, String email, int age);
    Customer createCustomer(Customer customer);
    void updateCustomer(Long customerId, Customer updatedCustomer);
    void deleteCustomer(Long customerId);
    List<Account> getCustomerAccounts(Long customerId);
    Account addAccountToCustomer(Long customerId, Account account);
}
