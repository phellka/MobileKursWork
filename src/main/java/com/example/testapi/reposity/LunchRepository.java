package com.example.testapi.reposity;

import com.example.testapi.entity.Lunch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LunchRepository extends CrudRepository<Lunch, Long> {
    @Override
    Optional<Lunch> findById(Long testId);
}
