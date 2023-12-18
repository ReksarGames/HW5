package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.danit.springrest.dao.AccountRepository;
import com.danit.springrest.enums.Currency;
import com.danit.springrest.model.Account;
import com.danit.springrest.model.Customer;
import com.danit.springrest.service.DefaultAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

class DefaultAccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private DefaultAccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccountForCustomer() {
        Customer customer = new Customer("John Doe", "john@example.com", 30);
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Account account = accountService.createAccountForCustomer(customer, Currency.USD);

        assertNotNull(account);
        assertEquals(customer, account.getCustomer());
        assertEquals(Currency.USD, account.getCurrency());
    }

    @Test
    void testUpdateAccount() {
        Account existingAccount = new Account(Currency.USD, new Customer("John Doe", "john@example.com", 30));
        existingAccount.setId(1L);
        Account updatedAccount = new Account(Currency.EUR, existingAccount.getCustomer());
        updatedAccount.setId(1L);

        when(accountRepository.existsById(1L)).thenReturn(true);
        when(accountRepository.save(updatedAccount)).thenReturn(updatedAccount);

        accountService.updateAccount("1", updatedAccount);

        assertEquals(Currency.EUR, existingAccount.getCurrency());
    }

    @Test
    void testUpdateAccountNotFound() {
        when(accountRepository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> accountService.updateAccount("1", new Account()));
    }

    @Test
    void testWithdrawMoneySufficientFunds() {
        Account account = new Account(Currency.USD, new Customer("John Doe", "john@example.com", 30));
        account.setBalance(100.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        accountService.withdrawMoney("1", 50.0);

        assertEquals(50.0, account.getBalance());
    }

    @Test
    void testWithdrawMoneyInsufficientFunds() {
        Account account = new Account(Currency.USD, new Customer("John Doe", "john@example.com", 30));
        account.setBalance(30.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(IllegalArgumentException.class, () -> accountService.withdrawMoney("1", 50.0));
    }

    @Test
    void testTransferMoney() {
        Account fromAccount = new Account(Currency.USD, new Customer("John Doe", "john@example.com", 30));
        fromAccount.setBalance(100.0);

        Account toAccount = new Account(Currency.USD, new Customer("Jane Doe", "jane@example.com", 25));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(toAccount));

        accountService.transferMoney("1", "2", 50.0);

        assertEquals(50.0, fromAccount.getBalance());
        assertEquals(50.0, toAccount.getBalance());
    }

    @Test
    void testTransferMoneyInsufficientFunds() {
        Account fromAccount = new Account(Currency.USD, new Customer("John Doe", "john@example.com", 30));
        fromAccount.setBalance(30.0);

        Account toAccount = new Account(Currency.USD, new Customer("Jane Doe", "jane@example.com", 25));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(toAccount));

        assertThrows(IllegalArgumentException.class, () -> accountService.transferMoney("1", "2", 50.0));
    }
}
