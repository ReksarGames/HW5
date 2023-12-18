package com.danit.springrest.repository;

import com.danit.springrest.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByName(String name);
}
