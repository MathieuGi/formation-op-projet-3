package com.girardon.chatop.service;

import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveUser(User user) throws Exception {
         userRepository.save(user);
    }
}
