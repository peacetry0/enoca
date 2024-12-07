package com.peace.challenge.controllers;

import com.peace.challenge.dto.request.customer.CreateCustomerRequest;
import com.peace.challenge.dto.request.customer.UpdateCustomerRequest;
import com.peace.challenge.dto.response.customer.CustomerDTO;
import com.peace.challenge.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1.0/customers")
@AllArgsConstructor
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        return ResponseEntity.ok(customerDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable final Long id) {

        CustomerDTO customerDTO = customerService.getCustomerById(id);

        return ResponseEntity.ok(customerDTO);
    }


    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody @Valid final CreateCustomerRequest createCustomerRequest) {

        CustomerDTO customerDTO = customerService.addCustomer(createCustomerRequest);

        return ResponseEntity.ok(customerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable final Long id, @RequestBody @Valid final UpdateCustomerRequest updateCustomerRequest) {

        CustomerDTO customerDTO = customerService.updateCustomer(id, updateCustomerRequest);

        return ResponseEntity.ok(customerDTO) ;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable final Long id) {

        customerService.deleteCustomerById(id);

        return ResponseEntity.noContent().build();
    }
}
