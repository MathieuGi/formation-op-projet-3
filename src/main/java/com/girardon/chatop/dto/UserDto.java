package com.girardon.chatop.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String created_at;
    private String updated_at;
}
