package com.example.mymarket.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderBriefInfo {
    private Long id;
    private String orderStatus;
    private Double totalPrice;
    private String createdAt;
}
