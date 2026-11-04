package com.girardon.chatop.service;

import com.girardon.chatop.dto.RentalCreateRequest;
import com.girardon.chatop.exception.EntityNotFoundException;
import com.girardon.chatop.mapper.RentalMapper;
import com.girardon.chatop.model.Rental;
import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final PictureService pictureService;
    private final UserService userService;

    public void saveRental(RentalCreateRequest rentalRequest) {
        Rental rental = rentalMapper.toRental(rentalRequest);

        String picture = pictureService.savePicture(rentalRequest.getPicture());
        rental.setPicture(picture);

        User user = userService.me();
        rental.setOwnerId(user.getId());

        rentalRepository.save(rental);

    }

    public void updateRental(int id, Rental rental) {
        User user = userService.me();
        Rental bddRental = rentalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("This rental " +
                "doesn't exists"));

        if (bddRental.getOwnerId() != user.getId()) {
            throw new RequestRejectedException("You can't update a rental you didn't create");
        }

        rental.setId(bddRental.getId());
        rental.setOwnerId(bddRental.getOwnerId());
        rentalRepository.save(rental);

    }

    public Iterable<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(int id) {
        return rentalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rental not found"));
    }
}
