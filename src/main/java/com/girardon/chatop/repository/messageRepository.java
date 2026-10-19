package com.girardon.chatop.repository;

import com.girardon.chatop.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface messageRepository extends CrudRepository<Message, Integer> {
}
