package com.pageport.pageport.dto;

import com.pageport.pageport.type.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderStatusUpdateRequest {
    private OrderStatus status;
} 