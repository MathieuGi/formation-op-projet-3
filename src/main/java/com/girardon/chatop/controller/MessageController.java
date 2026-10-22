package com.girardon.chatop.controller;

import com.girardon.chatop.dto.MessageRequest;
import com.girardon.chatop.dto.MessageResponse;
import com.girardon.chatop.service.MessageService;
import com.girardon.chatop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @PostMapping("")
    public ResponseEntity<MessageResponse> saveMessage(@Valid @RequestBody MessageRequest request,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        MessageResponse response = messageService.saveMessage(request, userDetails);
        return ResponseEntity.ok(response);
    }
}
