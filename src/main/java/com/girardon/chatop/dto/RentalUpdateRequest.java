package com.girardon.chatop.dto;

import lombok.Data;

@Data
public class RentalUpdateRequest {
    private int id;
    private String name;
    private Long surface;
    private Long price;
    private String description;
}
