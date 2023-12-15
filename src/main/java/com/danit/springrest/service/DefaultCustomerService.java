package com.danit.springrest.service;

import com.danit.springrest.dao.CustomerRepository;
import com.danit.springrest.model.Account;
import com.danit.springrest.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> getAllCustomers(PageRequest pageRequest) {
        return customerRepository.findAll(pageRequest);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }
    @Override
    public Customer createCustomer(String name, String email, int age) {
        return customerRepository.saveAndFlush(new Customer(name, email, age));
    }
    @Override
    public void updateCustomer(Long customerId, Customer updatedCustomer) {
        if (customerRepository.existsById(customerId)) {
            updatedCustomer.setId(customerId);
            customerRepository.save(updatedCustomer);
        } else {
            throw new IllegalArgumentException("Account not found with ID: " + customerId);
        }
    }
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
    @Override
    public List<Account> getCustomerAccounts(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return customer.getAccounts();
    }

    @Override
    public Account addAccountToCustomer(Long customerId, Account account) {
        Customer customer = getCustomerById(customerId);
        account.setCustomer(customer);
        customer.getAccounts().add(account);
        customerRepository.save(customer);
        return account;
    }
}
