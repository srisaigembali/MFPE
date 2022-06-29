package cts.mfpe.manager.controllers;

import cts.mfpe.manager.entities.Customer;
import cts.mfpe.manager.entities.Executive;
import cts.mfpe.manager.exceptions.CustomerNotFoundException;
import cts.mfpe.manager.exceptions.ExecutiveNotFoundException;
import cts.mfpe.manager.services.ExecutiveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ExecutiveControllerTest {

    @InjectMocks
    ExecutiveController executiveController;

    @Mock
    ExecutiveService executiveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createExecutive() throws Exception {
        Executive executive = mockExecutive();
        doNothing().when(executiveService).createExecutive(any(Executive.class));
        assertEquals(HttpStatus.CREATED, executiveController.createExecutive(mockExecutive()).getStatusCode());
    }

    @Test
    void getAllExecutives() {
        when(executiveService.getAllExecutives()).thenReturn(new ArrayList<Executive>());
        assertEquals(HttpStatus.OK, executiveController.getAllExecutives().getStatusCode());
    }

    private Executive mockExecutive() {
        Executive e = new Executive();
        e.setId(1);
        e.setName("ABCD");
        e.setLocality("VVIP Mall");
        e.setContactNumber(987654321L);
        e.setEmailId("test@example.com");
        return e;
    }

    @Test
    void getAllExecutivesByLocality_Exception() throws Exception {
        when(executiveService.getAllExecutivesByLocality(anyString())).thenReturn(new ArrayList<Executive>());
        assertThrows(ExecutiveNotFoundException.class, () -> executiveController.getAllExecutivesByLocality("VVIP Mall"));
    }

    @Test
    void getAllExecutivesByLocality() throws Exception {
        List<Executive> p = new ArrayList<Executive>();
        p.add(new Executive());
        when(executiveService.getAllExecutivesByLocality(anyString())).thenReturn(p);
        assertEquals(HttpStatus.OK, executiveController.getAllExecutivesByLocality("").getStatusCode());
    }

    @Test
    void getAllCustomers() {
        when(executiveService.getAllCustomers()).thenReturn(new ArrayList<Customer>());
        assertEquals(HttpStatus.OK, executiveController.getAllCustomers().getStatusCode());
    }

    @Test
    void getCustomerById_Exception() throws Exception {
        when(executiveService.getCustomerById(anyInt())).thenReturn(null);
        assertThrows(CustomerNotFoundException.class, () -> executiveController.getCustomerById(1));
    }

    @Test
    void getCustomerById() throws Exception {
        when(executiveService.getCustomerById(anyInt())).thenReturn(mockCustomer());
        assertEquals(HttpStatus.OK, executiveController.getCustomerById(1).getStatusCode());
    }

    private Customer mockCustomer() {
        Customer c = new Customer();
        c.setId(1);
        c.setName("ABCD");
        c.setEmailid("test@example.com");
        c.setAddress("VVIP Mall");
        return c;
    }

    @Test
    void assignExecutive() {
        doNothing().when(executiveService).assignExecutive(anyInt(), anyInt());
        assertEquals(HttpStatus.OK, executiveController.assignExecutive(1, 1).getStatusCode());
    }
}