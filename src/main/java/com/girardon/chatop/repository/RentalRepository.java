package com.girardon.chatop.repository;

import com.girardon.chatop.model.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer> {
}
