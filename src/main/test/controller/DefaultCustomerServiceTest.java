package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.danit.springrest.dao.CustomerRepository;
import com.danit.springrest.model.Account;
import com.danit.springrest.model.Customer;
import com.danit.springrest.service.DefaultCustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


class DefaultCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private DefaultCustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer("John Doe", "john@example.com", 30);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testGetCustomerAccounts() {
        Customer customer = new Customer("John Doe", "john@example.com", 30);
        customer.setAccounts(Collections.singletonList(new Account()));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        List<Account> result = customerService.getCustomerAccounts(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testAddAccountToCustomer() {
        Customer customer = new Customer("John Doe", "john@example.com", 30);
        Account account = new Account();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        Account result = customerService.addAccountToCustomer(1L, account);

        assertNotNull(result);
        assertEquals(customer, result.getCustomer());
    }

    @Test
    void testGetByLogin() {
        Customer customer = new Customer("John Doe", "john@example.com", 30);
        when(customerRepository.findUsersByName("john@example.com")).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getByLogin("john@example.com");

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    void testGetByLoginNotFound() {
        when(customerRepository.findUsersByName("john@example.com")).thenReturn(Optional.empty());

        Optional<Customer> result = customerService.getByLogin("john@example.com");

        assertTrue(result.isEmpty());
    }
}