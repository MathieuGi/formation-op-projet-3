package com.girardon.chatop.mapper;

import com.girardon.chatop.dto.RegisterRequest;
import com.girardon.chatop.dto.UserDto;
import com.girardon.chatop.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @AfterMapping
    protected void convertNameToUpperCase(@MappingTarget User user) {
        user.setName(user.getName().toUpperCase());
    }

    public abstract User toUser(RegisterRequest registerRequest);

    @Mapping(target = "created_at", source = "user.createdAt")
    @Mapping(target = "updated_at", source = "user.updatedAt")
    public abstract UserDto toUserDto(User user);
}
