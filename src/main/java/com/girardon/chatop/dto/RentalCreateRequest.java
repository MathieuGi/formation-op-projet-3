package com.girardon.chatop.dto;

import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class RentalCreateRequest {
    private String name;
    private Long surface;
    private Long price;
    private String description;
}
