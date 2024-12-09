package com.peace.enoca.service;

import com.peace.enoca.convertor.CustomerMapper;
import com.peace.enoca.dto.request.customer.CreateCustomerRequest;
import com.peace.enoca.dto.request.customer.UpdateCustomerRequest;
import com.peace.enoca.exception.CustomerNotFoundException;
import com.peace.enoca.exception.EmailAlreadyExistsException;
import com.peace.enoca.model.Customer;
import com.peace.enoca.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<Customer> getAllCustomers() {

        final List<Customer> customers = customerRepository.findAll();

        return customers;
    }

    public Customer getCustomerById(final Long id) {

        final Customer customer = customerRepository.findById(id)

                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        ;

        return customer;
    }


    public Customer addCustomer(final CreateCustomerRequest createCustomerRequest) {

        if (customerRepository.existsByEmail(createCustomerRequest.email())) {

            throw new EmailAlreadyExistsException("Email already exists");
        }

        final Customer customer = customerMapper.toCustomer(createCustomerRequest);

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(final Long id, final UpdateCustomerRequest updateCustomerRequest) {

        final Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        customerMapper.updateEntityToCustomer(customer, updateCustomerRequest);

        return customerRepository.save(customer);

    }

    public void deleteCustomerById(final Long id) {

        if (customerRepository.existsById(id)) {

            customerRepository.deleteById(id);

        } else {
            throw new CustomerNotFoundException("Customer not found");
        }


    }
}