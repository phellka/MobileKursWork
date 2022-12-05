package com.example.testapi.service;

import com.example.testapi.dto.TestDto;
import com.example.testapi.entity.Test;
import com.example.testapi.reposity.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public TestDto getTest(Long testId) {
        var test = testRepository.findById(testId).orElseThrow(
                () -> new IllegalArgumentException(String.format("Test with id=%s not found", testId)));
        return map(test);

    }

    private TestDto map(Test entity) {
        return TestDto.builder()
                .name(entity.getName())
                .quantityQuestions(entity.getQuestions().size())
                .build();
    }

}
