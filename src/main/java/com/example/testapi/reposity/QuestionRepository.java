package com.example.testapi.reposity;

import com.example.testapi.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

    @Override
    Optional<Question> findById(Long aLong);
}
