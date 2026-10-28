package com.girardon.chatop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "rentals")
public class Rental extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private BigDecimal surface;

    private BigDecimal price;

    private String picture;

    @Length(max = 2000)
    private String description;

    @Column(name = "owner_id")
    private int ownerId;

}
