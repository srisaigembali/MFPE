package cts.mfpe.customer.controllers;

import cts.mfpe.customer.entities.Customer;
import cts.mfpe.customer.entities.Property;
import cts.mfpe.customer.entities.Requirement;
import cts.mfpe.customer.exceptions.CustomerNotFoundException;
import cts.mfpe.customer.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRequirements() {
        when(customerService.getAllRequirements()).thenReturn(new ArrayList<Requirement>());

        assertEquals(HttpStatus.OK, customerController.getAllRequirements().getStatusCode());
    }

    @Test
    void getAllCustomers() {
        when(customerService.getAllCustomers()).thenReturn(new ArrayList<Customer>());
        assertEquals(HttpStatus.OK, customerController.getAllCustomers().getStatusCode());
    }

    @Test
    void createCustomer() throws Exception {
        Customer mockCustomer = mockCustomer();
        doNothing().when(customerService).createCustomer(any(Customer.class));
        assertEquals(HttpStatus.CREATED, customerController.createCustomer(mockCustomer).getStatusCode());
    }

    private Customer mockCustomer() {
        Customer c = new Customer();
        c.setId(1);
        c.setName("ABCD");
        c.setEmailid("test@example.com");
        c.setAddress("VVIP Mall");
        c.setContactNumber(987654321L);
        return c;
    }

    @Test
    void getCustomerDetailsWhenCustomerNotNull() throws Exception {
        when(customerService.getCustomerDetails(anyInt())).thenReturn(mockCustomer());
        assertEquals(HttpStatus.OK, customerController.getCustomerDetails(1).getStatusCode());
    }

    @Test
    void getCustomerDetailsWhenCustomerNull() throws Exception {
        when(customerService.getCustomerDetails(anyInt())).thenReturn(null);
        assertThrows(CustomerNotFoundException.class, () -> customerController.getCustomerDetails(1));
    }

    @Test
    void getAllProperties() {
        when(customerService.getAllProperties()).thenReturn(new ArrayList<Property>());
        assertEquals(HttpStatus.OK, customerController.getAllProperties().getStatusCode());
    }

    @Test
    void assignRequirements() {
        doNothing().when(customerService).assignRequirements(anyInt(), anyInt());
        assertEquals(HttpStatus.OK, customerController.assignRequirements(1, 1).getStatusCode());
    }
}