package com.switchfully.order.api.user;

import com.switchfully.order.service.user.CustomerService;
import com.switchfully.order.service.support.dto.user.CustomerDTO;
import com.switchfully.order.service.support.wrapper.CustomerWrapper;
import com.switchfully.order.service.user.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.switchfully.order.domain.models.user.Feature.CAN_RETRIEVE_ALL_CUSTOMERS;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {
    private final CustomerService customerService;
    private final SecurityService securityService;

    private final Logger myLogger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService, SecurityService securityService) {
        this.customerService = customerService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void createCustomer(@RequestBody CustomerWrapper customerWrapper){
        myLogger.info("Adding a New Customer to the Database.");
        customerService.createCustomer(customerWrapper);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<CustomerDTO> getAllCustomers(@RequestHeader(required = false) String authorization){
        myLogger.info("Retrieving all Customers.");
        securityService.validateUser(authorization, CAN_RETRIEVE_ALL_CUSTOMERS);
        return customerService.getAllCustomers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", value = "{customerId}")
    public CustomerDTO getCustomerById(@PathVariable String customerId) {
        myLogger.info("Retrieving Customer with id " + customerId);
        return customerService.getCustomerById(customerId);
    }
}
