package cts.mfpe.customer.controllers;

import cts.mfpe.customer.clients.AuthorizationClient;
import cts.mfpe.customer.entities.Customer;
import cts.mfpe.customer.entities.Property;
import cts.mfpe.customer.entities.Requirement;
import cts.mfpe.customer.exceptions.AuthorizationException;
import cts.mfpe.customer.exceptions.CustomerNotFoundException;
import cts.mfpe.customer.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    private final String requestTokenHeader = "vdgekncr4uliu45otu4864hutigh";

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    @Mock
    AuthorizationClient authorizationClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRequirements() throws AuthorizationException {
        when(authorizationClient.authorizeTheRequestForCustomer(anyString())).thenReturn(true);
        when(customerService.getAllRequirements()).thenReturn(new ArrayList<Requirement>());
        assertEquals(HttpStatus.OK, customerController.getAllRequirements(requestTokenHeader).getStatusCode());
    }

    @Test
    void getAllRequirements_Exception() {
        when(authorizationClient.authorizeTheRequestForCustomer(anyString())).thenReturn(false);
        assertThrows(AuthorizationException.class, () -> customerController.getAllRequirements(requestTokenHeader));
    }

    @Test
    void getAllCustomers() throws AuthorizationException {
        when(authorizationClient.authorizeTheRequestForManager(anyString())).thenReturn(true);
        when(customerService.getAllCustomers()).thenReturn(new ArrayList<Customer>());
        assertEquals(HttpStatus.OK, customerController.getAllCustomers(requestTokenHeader).getStatusCode());
    }

    @Test
    void getAllCustomers_Exception() {
        when(authorizationClient.authorizeTheRequestForManager(anyString())).thenReturn(false);
        assertThrows(AuthorizationException.class, () -> customerController.getAllCustomers(requestTokenHeader));
    }

    @Test
    void createCustomer() throws Exception {
        when(authorizationClient.authorizeTheRequestForCustomer(anyString())).thenReturn(true);
        Customer mockCustomer = mockCustomer();
        doNothing().when(customerService).createCustomer(any(Customer.class));
        assertEquals(HttpStatus.CREATED, customerController.createCustomer(mockCustomer, requestTokenHeader).getStatusCode());
    }

    @Test
    void createCustomer_Exception() {
        when(authorizationClient.authorizeTheRequestForCustomer(anyString())).thenReturn(false);
        Customer mockCustomer = mockCustomer();
        assertThrows(AuthorizationException.class, () -> customerController.createCustomer(mockCustomer, requestTokenHeader));
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
        when(authorizationClient.authorizeTheRequest(anyString())).thenReturn(true);
        when(customerService.getCustomerDetails(anyInt())).thenReturn(mockCustomer());
        assertEquals(HttpStatus.OK, customerController.getCustomerDetails(1, requestTokenHeader).getStatusCode());
    }

    @Test
    void getCustomerDetailsWhenCustomerNotNull_Exception() {
        when(authorizationClient.authorizeTheRequest(anyString())).thenReturn(false);
        when(customerService.getCustomerDetails(anyInt())).thenReturn(mockCustomer());
        assertThrows(AuthorizationException.class, () -> customerController.getCustomerDetails(1, requestTokenHeader));
    }

    @Test
    void getCustomerDetailsWhenCustomerNull() throws Exception {
        when(customerService.getCustomerDetails(anyInt())).thenReturn(null);
        assertThrows(CustomerNotFoundException.class, () -> customerController.getCustomerDetails(1, requestTokenHeader));
    }

    @Test
    void getAllProperties() throws Exception {
        when(authorizationClient.authorizeTheRequestForCustomer(anyString())).thenReturn(true);
        when(customerService.getAllProperties(requestTokenHeader)).thenReturn(new ArrayList<Property>());
        assertEquals(HttpStatus.OK, customerController.getAllProperties(requestTokenHeader).getStatusCode());
    }

    @Test
    void getAllProperties_Exception() {
        when(authorizationClient.authorizeTheRequestForCustomer(anyString())).thenReturn(false);
        assertThrows(AuthorizationException.class, () -> customerController.getAllProperties(requestTokenHeader));
    }

    @Test
    void assignRequirements() throws AuthorizationException {
        when(authorizationClient.authorizeTheRequestForCustomer(anyString())).thenReturn(true);
        doNothing().when(customerService).assignRequirements(anyInt(), anyInt());
        assertEquals(HttpStatus.OK, customerController.assignRequirements(1, 1, requestTokenHeader).getStatusCode());
    }

    @Test
    void assignRequirements_Exception() {
        when(authorizationClient.authorizeTheRequestForCustomer(anyString())).thenReturn(false);
        assertThrows(AuthorizationException.class, () -> customerController.assignRequirements(1, 1, requestTokenHeader));
    }

}