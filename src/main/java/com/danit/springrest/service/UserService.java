package com.danit.springrest.service;

import com.danit.springrest.model.Customer;
import com.danit.springrest.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<Customer> getByLogin(@NonNull String login) {

        return userRepository.findByName(login);
    }

}