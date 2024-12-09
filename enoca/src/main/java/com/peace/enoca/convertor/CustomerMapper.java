package com.peace.enoca.convertor;

import com.peace.enoca.dto.request.customer.CreateCustomerRequest;
import com.peace.enoca.dto.request.customer.UpdateCustomerRequest;
import com.peace.enoca.model.Customer;
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



}
