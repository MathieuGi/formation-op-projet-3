package com.girardon.chatop.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RentalCreateRequest {
    private String name;
    private Long surface;
    private Long price;
    private String description;
    private MultipartFile picture;
}
