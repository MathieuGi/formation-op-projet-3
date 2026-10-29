package com.girardon.chatop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message extends AbstractAuditingEntity {
    @Column(name = "rental_id")
    private int rentalId;

    @Column(name = "user_id")
    private int userId;

    @Length(max = 2000)
    private String message;
}
