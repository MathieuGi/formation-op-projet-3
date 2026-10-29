package com.girardon.chatop.controller;

import com.girardon.chatop.dto.GenericMessageResponse;
import com.girardon.chatop.dto.MessageRequest;
import com.girardon.chatop.dto.MessageResponse;
import com.girardon.chatop.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/messages")
@Tag(name = "Messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("")
    @Operation(summary = "Send a message to the rental's owner", operationId = "saveMessage")
    public ResponseEntity<GenericMessageResponse> saveMessage(@Valid @RequestBody MessageRequest request,
                                                              @AuthenticationPrincipal UserDetails userDetails) {

        messageService.saveMessage(request, userDetails);
        return ResponseEntity.ok(new GenericMessageResponse("Message sent"));
    }
}
