package com.girardon.chatop.controller;

import com.girardon.chatop.exception.ErrorResponse;
import com.girardon.chatop.exception.UserExistsException;
import com.girardon.chatop.model.User;
import com.girardon.chatop.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/auth/register")
    @Operation(summary = "Register a new user", operationId = "registerUser")
    public ResponseEntity<String> registerUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User to create",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class),
                    examples = @ExampleObject(value = "{ 'name': 'Jean Dupond', " +
                            "'email': 'jean.dupond@gmail.com', " +
                            "'password': 'Dhe4jD'}")
            )
    ) @RequestBody User user) {

        String res = userService.saveUser(user);

        return ResponseEntity.ok("jwt");
    }

    @ExceptionHandler(value = UserExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserExistingException(UserExistsException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
