package com.example.testapi.reposity;

import com.example.testapi.entity.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {

    @Override
    Optional<Test> findById (Long testId);

}
