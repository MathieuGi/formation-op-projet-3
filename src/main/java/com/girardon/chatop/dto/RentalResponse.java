package com.girardon.chatop.dto;

import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RentalResponse {

    private int id;
    private String name;
    private Long surface;
    private Long price;
    private String picture;
    private String description;
    private int ownerId;
    private String createdAt;
    private String UpdatedAt;
}
