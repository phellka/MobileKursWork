package com.example.testapi.reposity;

import com.example.testapi.entity.Cutlery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CutleryRepository  extends CrudRepository<Cutlery, Long> {
    @Override
    Optional<Cutlery> findById(Long testId);
}