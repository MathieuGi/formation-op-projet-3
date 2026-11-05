package com.girardon.chatop.mapper;

import com.girardon.chatop.dto.RentalCreateRequest;
import com.girardon.chatop.dto.RentalDto;
import com.girardon.chatop.dto.RentalUpdateRequest;
import com.girardon.chatop.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class RentalMapper {
    @Mapping(source = "createdAt", target = "created_at")
    @Mapping(source = "updatedAt", target = "updated_at")
    public abstract RentalDto toRentalDto(Rental rental);

    @Mapping(ignore = true, target = "picture")
    public abstract Rental toRental(RentalCreateRequest rentalCreateRequest);

    public abstract Rental toRental(RentalUpdateRequest rentalUpdateRequest);
}
