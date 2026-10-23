package com.girardon.chatop.controller;

import com.girardon.chatop.dto.RentalCreateRequest;
import com.girardon.chatop.dto.RentalResponse;
import com.girardon.chatop.dto.RentalsResponse;
import com.girardon.chatop.service.PictureService;
import com.girardon.chatop.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final PictureService pictureService;
    private final RentalService rentalService;
    private final ModelMapper modelMapper;

    @PostMapping(path = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> saveRental(@ModelAttribute RentalCreateRequest rental,
                                             @AuthenticationPrincipal UserDetails userDetails) {

        rentalService.saveRental(rental, userDetails);
//        String picturePath;
//        try {
//            picturePath = pictureService.savePicture(rental.getPicture());
//        } catch (IOException e) {
//            return ResponseEntity.badRequest().body("An error occure with the image");
//        }


        return ResponseEntity.ok("Rental created");
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
}
