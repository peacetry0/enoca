package com.peace.enoca.controller;

import com.peace.enoca.dto.request.customer.CreateCustomerRequest;
import com.peace.enoca.dto.request.customer.UpdateCustomerRequest;
import com.peace.enoca.model.Customer;
import com.peace.enoca.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/customers")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> customerDTOS = customerService.getAllCustomers();

        return ResponseEntity.ok(customerDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable final Long id) {

        Customer customer = customerService.getCustomerById(id);

        return ResponseEntity.ok(customer);
    }


    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid final CreateCustomerRequest createCustomerRequest) {

        Customer customer = customerService.addCustomer(createCustomerRequest);

        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable final Long id, @RequestBody @Valid final UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = customerService.updateCustomer(id, updateCustomerRequest);

        return ResponseEntity.ok(customer) ;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable final Long id) {

        customerService.deleteCustomerById(id);

        return ResponseEntity.noContent().build();
    }
}
