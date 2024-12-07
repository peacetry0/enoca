package com.peace.challenge.convertor;


import com.peace.challenge.dto.request.customer.CreateCustomerRequest;
import com.peace.challenge.dto.request.customer.UpdateCustomerRequest;
import com.peace.challenge.dto.response.customer.CustomerDTO;
import com.peace.challenge.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(final CreateCustomerRequest createCustomerRequest) {

        Customer customer = new Customer();
        customer.setName(createCustomerRequest.name());
        customer.setEmail(createCustomerRequest.email());
        customer.setPassword(createCustomerRequest.password());

        return customer;

    }

    public Customer updateEntityToCustomer(final Customer customer,final UpdateCustomerRequest updateCustomerRequest) {


        customer.setName(updateCustomerRequest.name());
        customer.setEmail(updateCustomerRequest.email());

        return customer;

    }

    public CustomerDTO toCustomerDTO(final Customer customer) {

        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail()) ;

    }
    public Customer toEntity(CustomerDTO customerDTO) {

        Customer customer = new Customer();
        customer.setId(customerDTO.id());
        customer.setName(customerDTO.name());
        customer.setEmail(customerDTO.email());

        return customer;
    }


}
