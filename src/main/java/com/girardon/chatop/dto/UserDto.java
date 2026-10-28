package com.girardon.chatop.dto;

import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String created_at;
    private String updated_at;
}
