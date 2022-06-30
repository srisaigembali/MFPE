package cts.mfpe.manager.controllers;

import cts.mfpe.manager.clients.AuthorizationClient;
import cts.mfpe.manager.entities.Customer;
import cts.mfpe.manager.entities.Executive;
import cts.mfpe.manager.exceptions.AuthorizationException;
import cts.mfpe.manager.exceptions.CustomerNotFoundException;
import cts.mfpe.manager.exceptions.ExecutiveNotFoundException;
import cts.mfpe.manager.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private AuthorizationClient authorizationClient;

    @PostMapping("/createExecutive")
    public ResponseEntity<String> createExecutive(@RequestBody @Valid Executive executive,
                                                  @RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
        if (authorizationClient.authorizeTheRequestForManager(requestTokenHeader)) {
            managerService.createExecutive(executive);
            return new ResponseEntity<String>("Executive Created Successfully!", HttpStatus.CREATED);
        }
        throw new AuthorizationException("Not Allowed");
    }

    @GetMapping("/getAllExecutives")
    public ResponseEntity<List<Executive>> getAllExecutives(
            @RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
            throws AuthorizationException {
        if (authorizationClient.authorizeTheRequestForManager(requestTokenHeader)
                || authorizationClient.authorizeTheRequestForExecutive(requestTokenHeader)) {
            return ResponseEntity.ok(managerService.getAllExecutives());
        }
        throw new AuthorizationException("Not Allowed");
    }

    @GetMapping("/getAllExecutivesByLocality/{locality}")
    public ResponseEntity<List<Executive>> getAllExecutivesByLocality(@PathVariable String locality,
                                                                      @RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
        List<Executive> executives = managerService.getAllExecutivesByLocality(locality);
        if (executives.size() == 0) {
            throw new ExecutiveNotFoundException("Executives in locality " + locality + " not found");
        }
        if (authorizationClient.authorizeTheRequestForManager(requestTokenHeader)
                || authorizationClient.authorizeTheRequestForExecutive(requestTokenHeader)) {
            return ResponseEntity.ok(executives);
        }
        throw new AuthorizationException("Not Allowed");
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
            throws Exception {
        if (authorizationClient.authorizeTheRequestForManager(requestTokenHeader)) {
            return ResponseEntity.ok(managerService.getAllCustomers(requestTokenHeader));
        }
        throw new AuthorizationException("Not Allowed");
    }

    @GetMapping("/getCustomersById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id,
                                                    @RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
        Customer customer = managerService.getCustomerById(id, requestTokenHeader);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }
        if (authorizationClient.authorizeTheRequestForManager(requestTokenHeader)) {
            return ResponseEntity.ok(customer);
        }
        throw new AuthorizationException("Not Allowed");
    }

    @PutMapping("/{executiveId}/assignExecutive/{customerId}")
    public ResponseEntity<String> assignExecutive(@PathVariable("executiveId") int execid,
                                                  @PathVariable("customerId") int custid,
                                                  @RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
            throws Exception {
        if (authorizationClient.authorizeTheRequestForManager(requestTokenHeader)) {
            managerService.assignExecutive(execid, custid, requestTokenHeader);
            return new ResponseEntity<>("Executive Assigned Successfully!", HttpStatus.OK);
        }
        throw new AuthorizationException("Not Allowed");
    }
}
