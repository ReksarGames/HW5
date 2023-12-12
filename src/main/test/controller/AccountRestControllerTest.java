package controller;

import com.danit.springrest.enums.Currency;
import com.danit.springrest.model.Account;
import com.danit.springrest.model.Customer;
import com.danit.springrest.service.AccountService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountRestControllerTest {
    private final AccountService accountService;

    public AccountRestControllerTest(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Page<Account> getAllAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return accountService.getAllAccounts(pageRequest);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable String accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Account> createAccount(
            @ApiParam(value = "Customer details", required = true) @RequestBody Customer customer) {
        System.out.println("Received Customer: " + customer);
        Account createdAccount = accountService.createAccountForCustomer(customer,Currency.UAH);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAccount.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdAccount);
    }
//    @PostMapping("/createAccount")
//    public ResponseEntity<Account> createAccount(@RequestBody Customer customer) {
//        System.out.println("Received Customer: " + customer); // Вивід для відлагодження
//        Account createdAccount = new Account(Currency.UAH,customer);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(createdAccount.getId())
//                .toUri();
//        return ResponseEntity.ok().body(createdAccount);
//    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Void> updateAccount(@PathVariable String accountId, @RequestBody Account updatedAccount) {
        accountService.updateAccount(accountId, updatedAccount);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<Void> depositMoney(@PathVariable String accountId, @RequestParam double amount) {
        accountService.depositMoney(accountId, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<Void> withdrawMoney(@PathVariable String accountId, @RequestParam double amount) {
        accountService.withdrawMoney(accountId, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferMoney(@RequestParam String fromAccountId, @RequestParam String toAccountId, @RequestParam double amount) {
        accountService.transferMoney(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok().build();
    }

}
