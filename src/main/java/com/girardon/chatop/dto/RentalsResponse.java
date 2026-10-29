package com.girardon.chatop.dto;

import lombok.Data;

import java.util.List;

@Data
public class RentalsResponse {
    private List<RentalDto> rentals;
}
