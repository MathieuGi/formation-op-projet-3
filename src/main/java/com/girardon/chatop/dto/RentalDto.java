package com.girardon.chatop.dto;

import lombok.Data;

@Data
public class RentalDto {
    private int id;
    private String name;
    private Long surface;
    private Long price;
    private String picture;
    private String description;
    private int ownerId;
    private String created_at;
    private String updated_at;
}
