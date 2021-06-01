package com.example.mymarket.model;

import lombok.Data;

@Data
public class UserDeliveryAddress {
    private String city;
    private String street;
    private String house;
    private String postalCode;
    private String apt;
    private String addInfo;
}
