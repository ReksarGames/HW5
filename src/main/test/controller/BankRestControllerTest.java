package controller;

import com.danit.springrest.dto.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BankRestControllerTest {
    private final BankFacade bankFacade;

    public BankRestControllerTest(BankFacade bankFacade) {
        this.bankFacade = bankFacade;
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CustomerRequestDTO customerRequest) {
        CustomerResponseDTO createdCustomer = bankFacade.createCustomer(customerRequest);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/account")
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody @Valid AccountRequestDTO accountRequest) {
        AccountResponseDTO createdAccount = bankFacade.createAccount(accountRequest);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

}
