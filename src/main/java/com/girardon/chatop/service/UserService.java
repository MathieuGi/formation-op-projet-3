package com.girardon.chatop.service;

import com.girardon.chatop.exception.UserExistsException;
import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String saveUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            userRepository.save(user);
            return "User added successfully";
        } else {
            throw new UserExistsException("this email is already used !");
        }
    }
}
