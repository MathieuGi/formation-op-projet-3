package com.girardon.chatop.controller;

import com.girardon.chatop.dto.GenericMessageResponse;
import com.girardon.chatop.dto.RentalCreateRequest;
import com.girardon.chatop.dto.RentalResponse;
import com.girardon.chatop.dto.RentalsResponse;
import com.girardon.chatop.model.Rental;
import com.girardon.chatop.service.PictureService;
import com.girardon.chatop.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/rentals")
@Tag(name = "Rentals")
@RequiredArgsConstructor
public class RentalController {
    private final PictureService pictureService;
    private final RentalService rentalService;
    private final ModelMapper modelMapper;

    @PostMapping(path = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Create a new rental", operationId = "saveRental")
    public ResponseEntity<GenericMessageResponse> saveRental(@ModelAttribute RentalCreateRequest rentalRequest,
                                                             @RequestPart MultipartFile picture,
                                                             @AuthenticationPrincipal UserDetails userDetails) {

        String pictureName;
        Rental rental = modelMapper.map(rentalRequest, Rental.class);

        pictureName = pictureService.savePicture(picture);

        rental.setPicture("http://localhost:3001/api/images/" + pictureName);
        rentalService.saveRental(rental, userDetails);


        return ResponseEntity.ok(new GenericMessageResponse("Rental saved"));
    }

    @PutMapping(path = "/{rentalId}")
    @Operation(summary = "Update a rental", operationId = "updateRental")
    public ResponseEntity<GenericMessageResponse> updateRental(@RequestBody RentalCreateRequest rentalRequest,
                                                               @AuthenticationPrincipal UserDetails userDetails,
                                                               @PathVariable int rentalId) {
        Rental rental = modelMapper.map(rentalRequest, Rental.class);

        rental.setId(rentalId);
        rentalService.saveRental(rental, userDetails);

        return ResponseEntity.ok(new GenericMessageResponse("Rental updated"));
    }

    @GetMapping(path = "")
    @Operation(summary = "Retrieve all rentals", operationId = "GetRentals")
    public ResponseEntity<RentalsResponse> getRentals() {
        RentalsResponse response = new RentalsResponse();
        List<RentalResponse> rentals = new ArrayList<>();

        rentalService.getRentals().forEach(rental -> rentals.add(modelMapper.map(rental, RentalResponse.class)));
        response.setRentals(rentals);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Retrieve a rental by its id", operationId = "getRental")
    public ResponseEntity<RentalResponse> getRental(@PathVariable int id) {
        Rental rental = rentalService.getRentalById(id);

        return ResponseEntity.ok(modelMapper.map(rental, RentalResponse.class));
    }
}
