package com.switchfully.order.api;

import com.switchfully.order.service.CustomerService;
import com.switchfully.order.service.wrapper.CustomerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {
    private final CustomerService customerService;

    private final Logger myLogger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", value = "")
    public void createCustomer(@RequestBody CustomerWrapper customerWrapper){
        myLogger.info("Adding a New Customer to the Database.");
        customerService.createCustomer(customerWrapper);
    }
}
