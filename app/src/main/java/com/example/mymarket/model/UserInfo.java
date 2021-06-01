package com.example.mymarket.model;

import java.util.List;

import lombok.Data;

@Data
public class UserInfo {
    private String login;
    private String fio;
    private String phone;
    private String email;
    private List<UserDeliveryAddress> addresses;
}
