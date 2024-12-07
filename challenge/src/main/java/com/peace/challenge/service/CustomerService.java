package com.peace.challenge.service;

import com.peace.challenge.convertor.CustomerMapper;
import com.peace.challenge.dto.request.customer.CreateCustomerRequest;
import com.peace.challenge.dto.request.customer.UpdateCustomerRequest;
import com.peace.challenge.dto.response.customer.CustomerDTO;
import com.peace.challenge.exception.CustomerNotFoundException;
import com.peace.challenge.exception.EmailAlreadyExistsException;
import com.peace.challenge.model.Customer;
import com.peace.challenge.repository.CustomerRepository;
import lombok.AllArgsConstructor;
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

    public List<CustomerDTO> getAllCustomers() {

        final List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customerMapper::toCustomerDTO)
                .toList();
    }

    public CustomerDTO getCustomerById(final Long id) {

        final Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        ;

        return customerMapper.toCustomerDTO(customer);
    }


    public CustomerDTO addCustomer(final CreateCustomerRequest createCustomerRequest) {

        if (customerRepository.existsByEmail(createCustomerRequest.email())) {

            throw new EmailAlreadyExistsException("Email already exists");
        }

        final Customer customer = customerMapper.toCustomer(createCustomerRequest);

        final CustomerDTO customerDto = customerMapper.toCustomerDTO(customerRepository.save(customer));

        return customerDto;
    }

    public CustomerDTO updateCustomer(final Long id, final UpdateCustomerRequest updateCustomerRequest) {

        final Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        ;

        customerMapper.updateEntityToCustomer(customer, updateCustomerRequest);

        return customerMapper.toCustomerDTO(customerRepository.save(customer));

    }

    public void deleteCustomerById(final Long id) {

        if (customerRepository.existsById(id)) {

            customerRepository.deleteById(id);

        } else {
            throw new CustomerNotFoundException("Customer not found");
        }


    }
}
