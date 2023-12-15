package com.danit.springrest.resources;

import com.danit.springrest.enums.Currency;
import com.danit.springrest.model.Account;
import com.danit.springrest.model.Customer;
import com.danit.springrest.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Page;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerRestController {
    private final CustomerService customerService;
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }
    @GetMapping("/getCustomer")
    public Page<Customer> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return customerService.getAllCustomers(pageRequest);
    }

    @GetMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer addCustomer = customerService.addCustomer(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).body(addCustomer);
    }
//    @GetMapping("/createCustomer")
//    public ResponseEntity<Customer> createCustomer(@RequestParam String name, @RequestParam String email, @RequestParam int age){
//        Customer createdCustomer = customerService.createCustomer(name, email, age);
//        createdCustomer.setPassword(encodedPassword);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(createdCustomer.getId())
//                .toUri();
//        return ResponseEntity.created(location).body(createdCustomer);
//    }
    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable Long customerId, @RequestBody Customer updatedCustomer) {
        customerService.updateCustomer(customerId, updatedCustomer);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<List<Account>> getCustomerAccounts(@PathVariable Long customerId) {
        List<Account> accounts = customerService.getCustomerAccounts(customerId);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/{customerId}/accounts")
    public ResponseEntity<Account> addAccountToCustomer(@PathVariable Long customerId, @RequestBody Account account) {
        Account addedAccount = customerService.addAccountToCustomer(customerId, account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedAccount.getId())
                .toUri();
        return ResponseEntity.created(location).body(addedAccount);
    }
}
