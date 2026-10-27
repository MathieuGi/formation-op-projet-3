package com.girardon.chatop.service;

import com.girardon.chatop.exception.EntityNotFoundException;
import com.girardon.chatop.model.Rental;
import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.RentalRepository;
import com.girardon.chatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public void saveRental(Rental rental, UserDetails userDetails) {
        User user =
                userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException(
                        "This user doesn't exist"));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        rental.setOwnerId(user.getId());

        if (rental.getId() == 0) {
            rental.setCreatedAt(timestamp);
        }

        rental.setUpdatedAt(timestamp);

        rentalRepository.save(rental);

    }

    public Iterable<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(int id) {
        return rentalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rental not found"));
    }
}
