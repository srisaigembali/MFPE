package cts.mfpe.customer.services;

import cts.mfpe.customer.clients.PropertyServiceClient;
import cts.mfpe.customer.entities.Customer;
import cts.mfpe.customer.entities.Property;
import cts.mfpe.customer.entities.Requirement;
import cts.mfpe.customer.exceptions.CustomerAlredyExistsException;
import cts.mfpe.customer.repos.CustomerRepository;
import cts.mfpe.customer.repos.RequirementRepository;
import feign.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerServiceImpl;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    RequirementRepository requirementRepository;

    @Mock
    PropertyServiceClient propertyServiceClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRequirements() {
        when(requirementRepository.findAll()).thenReturn(new ArrayList<Requirement>());
        customerServiceImpl.getAllRequirements();
        verify(requirementRepository, times(1)).findAll();
    }

    private Requirement mockRequirement() {
        Requirement r = new Requirement();
        r.setId(1);
        r.setLocality("Park");
        r.setPropertyType("Villa");
        r.setBudget(500000.0);
        return r;
    }

    @Test
    void getAllCustomers() {
        when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
        customerServiceImpl.getAllCustomers();
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void createCustomerWhenCustomerDoesNotExist() throws Exception {
        Customer mockCustomer = mockCustomer();
        customerServiceImpl.createCustomer(mockCustomer);
        verify(customerRepository, times(1)).save(mockCustomer);
    }

    @Test
    void createCustomerWhenCustomerAlreadyExists() throws Exception {
        Customer customer = mockCustomer();
        when(customerServiceImpl.getAllCustomers()).thenReturn(mockCustomerList());
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        assertThrows(CustomerAlredyExistsException.class, () -> customerServiceImpl.createCustomer(mockCustomer()));
    }

    private List<Customer> mockCustomerList() {
        List<Customer> l = new ArrayList<>();
        l.add(mockCustomer());
        return l;
    }

    @Test
    void getCustomerDetails_result_is_not_empty() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(mockCustomer()));
        assertEquals(mockCustomer(), customerServiceImpl.getCustomerDetails(1));

    }

    @Test
    void getCustomerDetails_result_is_empty() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertNull(customerServiceImpl.getCustomerDetails(1));

    }

    @Test
    void getAllProperties() {
        when(propertyServiceClient.getAllProperties()).thenReturn(new ArrayList<Property>());
        customerServiceImpl.getAllProperties();
        verify(propertyServiceClient, times(1)).getAllProperties();
    }

    @Test
    void assignRequirements() {
        Customer c = mockCustomer();
        Requirement r = mockRequirement();
        when(customerRepository.findById(Optional.of(anyInt()).get())).thenReturn(Optional.of(mockCustomer()));
        when(requirementRepository.findById(Optional.of(anyInt()).get())).thenReturn(Optional.of(r));
        when(customerRepository.save(any(Customer.class))).thenReturn(c);
        customerServiceImpl.assignRequirements(1, 1);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    private Customer mockCustomer() {
        Customer c = new Customer();
        c.setId(1);
        c.setName("ABCD");
        c.setEmailid("test@example.com");
        c.setAddress("VVIP Mall");
        c.setContactNumber(987654321L);

        c.setRequirements(new HashSet<Requirement>(Set.of(new Requirement())));
        return c;
    }
}