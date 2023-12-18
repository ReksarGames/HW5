package com.danit.springrest.dao;

import com.danit.springrest.enums.Currency;
import com.danit.springrest.model.Account;
import com.danit.springrest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Используйте именованный запрос
    @Query("SELECT c FROM Customer c WHERE c.id = ?1")
    Customer infoById(Long id);

    // Используйте именованный запрос
    @Query("SELECT a FROM Account a WHERE a.customer.id = ?1")
    List<Account> getAccountsByCustomerId(Long customerId);
    Optional<Customer> findUsersByName(String name);

}