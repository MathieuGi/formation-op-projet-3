package com.girardon.chatop.controller;

import com.girardon.chatop.dto.*;
import com.girardon.chatop.mapper.RentalMapper;
import com.girardon.chatop.model.Rental;
import com.girardon.chatop.service.PictureService;
import com.girardon.chatop.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/rentals")
@Tag(name = "Rentals")
@RequiredArgsConstructor
public class RentalController {
    private final PictureService pictureService;
    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    @PostMapping(path = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Create a new rental", operationId = "saveRental")
    public ResponseEntity<GenericMessageResponse> saveRental(@ModelAttribute RentalCreateRequest rentalRequest,
                                                             @AuthenticationPrincipal UserDetails userDetails) {
        String pictureName;
        Rental rental = rentalMapper.toRental(rentalRequest);

        pictureName = pictureService.savePicture(rentalRequest.getPicture());
        rental.setPicture("http://localhost:3001/api/images/" + pictureName);

        rentalService.saveRental(rental, userDetails);


        return ResponseEntity.ok(new GenericMessageResponse("Rental saved"));
    }

    @PutMapping(path = "/{rentalId}")
    @Operation(summary = "Update a rental", operationId = "updateRental")
    public ResponseEntity<GenericMessageResponse> updateRental(@RequestBody RentalUpdateRequest rentalRequest,
                                                               @AuthenticationPrincipal UserDetails userDetails,
                                                               @PathVariable int rentalId) {
        Rental rental = rentalMapper.toRental(rentalRequest);

        rentalService.updateRental(rentalId, rental, userDetails);

        return ResponseEntity.ok(new GenericMessageResponse("Rental updated"));
    }

    @GetMapping(path = "")
    @Operation(summary = "Retrieve all rentals", operationId = "GetRentals")
    public ResponseEntity<RentalsResponse> getRentals() {
        RentalsResponse response = new RentalsResponse();
        List<RentalDto> rentals = new ArrayList<>();

        rentalService.getRentals().forEach(rental -> rentals.add(rentalMapper.toRentalDto(rental)));
        response.setRentals(rentals);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Retrieve a rental by its id", operationId = "getRental")
    public ResponseEntity<RentalDto> getRental(@PathVariable int id) {
        Rental rental = rentalService.getRentalById(id);

        return ResponseEntity.ok(rentalMapper.toRentalDto(rental));
    }
}
