package com.danit.springrest.resources;

import ch.qos.logback.core.model.Model;
import com.danit.springrest.dto.CustomerRequestDTO;
import com.danit.springrest.dto.CustomerResponseDTO;
import com.danit.springrest.model.Customer;
import com.danit.springrest.service.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final CustomerService customerService;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestParam String name,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam int age,
                                   Model model) {
        String encodedPassword = passwordEncoder.encode(password);
        Customer newCustomer = new Customer(name, email, age);
        newCustomer.setPasswordEncoded(encodedPassword);
        customerService.addCustomer(newCustomer);

        // Повертаємо сторінку або редирект
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).body(newCustomer);
    }
}
