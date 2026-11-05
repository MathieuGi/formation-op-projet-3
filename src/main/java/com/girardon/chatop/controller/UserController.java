package com.girardon.chatop.controller;

import com.girardon.chatop.dto.UserDto;
import com.girardon.chatop.mapper.UserMapper;
import com.girardon.chatop.model.User;
import com.girardon.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/user")
@Tag(name = "Users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;

    @GetMapping("{userId}")
    @Operation(summary = "Get user information", operationId = "getUser")
    public ResponseEntity<UserDto> getUser(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        UserDto userDto = userMapper.toUserDto(user);

        return ResponseEntity.ok(userDto);
    }
}
