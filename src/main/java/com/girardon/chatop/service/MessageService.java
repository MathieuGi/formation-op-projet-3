package com.girardon.chatop.service;

import com.girardon.chatop.model.Message;
import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;


    public void saveMessage(Message message, UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());

        if (user.getId() != message.getUserId()) {
            throw new RequestRejectedException("UserId shouldn't be set manually");
        }


        messageRepository.save(message);
    }
}
