package com.girardon.chatop.controller;

import com.girardon.chatop.dto.RentalCreateRequest;
import com.girardon.chatop.dto.RentalResponse;
import com.girardon.chatop.dto.RentalsResponse;
import com.girardon.chatop.model.Rental;
import com.girardon.chatop.service.PictureService;
import com.girardon.chatop.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final PictureService pictureService;
    private final RentalService rentalService;
    private final ModelMapper modelMapper;

    private final String UPLOAD_DIRECTORY = "src/main/resources/static/";

    @PostMapping(path = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> saveRental(@ModelAttribute RentalCreateRequest rentalRequest,
                                             @RequestPart MultipartFile picture,
                                             @AuthenticationPrincipal UserDetails userDetails) {


        String picturePath;
        Rental rental = modelMapper.map(rentalRequest, Rental.class);
        try {
            picturePath = pictureService.savePicture(picture, UPLOAD_DIRECTORY);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("An error occurs with the image");
        }

        rental.setPicture(picturePath);
        rentalService.saveRental(rental, userDetails);


        return ResponseEntity.ok("Rental created");
    }

    @PutMapping(path = "/{rentalId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateRental(@ModelAttribute RentalCreateRequest rentalRequest,
                                               @RequestPart MultipartFile picture,
                                               @AuthenticationPrincipal UserDetails userDetails,
                                               @PathVariable int rentalId) {
        String picturePath;
        Rental rental = modelMapper.map(rentalRequest, Rental.class);

        try {
            picturePath = pictureService.savePicture(picture, UPLOAD_DIRECTORY);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("An error occurs with the image");
        }

        rental.setPicture(picturePath);
        rental.setId(rentalId);
        rentalService.saveRental(rental, userDetails);

        return ResponseEntity.ok("Rental updated");
    }

    @GetMapping(path = "")
    public ResponseEntity<RentalsResponse> getRentals() {

        List<RentalResponse> rentals =
                StreamSupport.stream(rentalService.getRentals().spliterator(), false).toList().stream().map(
                        rental -> modelMapper.map(rental, RentalResponse.class)).toList();

        RentalsResponse response = new RentalsResponse();
        response.setRentals(rentals);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalResponse> getRental(@PathVariable int id) {
        Rental rental = rentalService.getRentalById(id);

        return ResponseEntity.ok(modelMapper.map(rental, RentalResponse.class));
    }
}
