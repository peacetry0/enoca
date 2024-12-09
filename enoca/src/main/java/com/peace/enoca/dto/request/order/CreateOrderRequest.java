package com.peace.enoca.dto.request.order;


import java.util.List;

public record CreateOrderRequest(

        Long customerId,
        List<Long> productIds

) {
}
