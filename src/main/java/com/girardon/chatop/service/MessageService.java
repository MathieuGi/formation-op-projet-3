package com.girardon.chatop.service;

import com.girardon.chatop.dto.MessageRequest;
import com.girardon.chatop.dto.MessageResponse;
import com.girardon.chatop.model.Message;
import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public void saveMessage(MessageRequest request, UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());

        if (user.getId() != request.getUserId()) {
            throw new RequestRejectedException("UserId shouldn't be set manually");
        }

        Message message = modelMapper.map(request, Message.class);

        messageRepository.save(message);
    }
}
