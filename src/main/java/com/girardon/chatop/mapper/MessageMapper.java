package com.girardon.chatop.mapper;

import com.girardon.chatop.dto.MessageRequest;
import com.girardon.chatop.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {
    @Mapping(source = "user_id", target = "userId")
    @Mapping(source = "rental_id", target = "rentalId")
    public abstract Message toMessage(MessageRequest messageRequest);
}
