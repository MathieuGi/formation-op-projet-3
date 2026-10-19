package com.girardon.chatop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
