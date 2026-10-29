package com.girardon.chatop.service;

import com.girardon.chatop.exception.EntityNotFoundException;
import com.girardon.chatop.model.Rental;
import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.RentalRepository;
import com.girardon.chatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public void saveRental(Rental rental, UserDetails userDetails) {
        User user =
                userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException(
                        "This user doesn't exist"));

        rental.setOwnerId(user.getId());

        rentalRepository.save(rental);

    }

    public void updateRental(int id, Rental rental, UserDetails userDetails) {
        User user =
                userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException(
                        "This user doesn't exist"));

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
