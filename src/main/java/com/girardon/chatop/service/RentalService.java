package com.girardon.chatop.service;

import com.girardon.chatop.dto.RentalCreateRequest;
import com.girardon.chatop.model.Rental;
import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.RentalRepository;
import com.girardon.chatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public void saveRental(RentalCreateRequest request, UserDetails userDetails) {
        Rental rental = modelMapper.map(request, Rental.class);
        User user =
                userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException(
                        "This user doesn't exist"));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        rental.setOwnerId(user.getId());
        rental.setCreatedAt(timestamp);
        rental.setUpdatedAt(timestamp);

        rentalRepository.save(rental);

    }
}
