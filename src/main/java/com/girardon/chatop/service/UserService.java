package com.girardon.chatop.service;

import com.girardon.chatop.exception.EntityNotFoundException;
import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("This user doesn't " +
                "exists"));
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("This user doesn't exists"));
    }
}
