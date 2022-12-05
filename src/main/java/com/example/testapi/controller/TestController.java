package com.example.testapi.controller;

import com.example.testapi.dto.TestDto;
import com.example.testapi.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping(value = "api/v1/tests/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable Long id) {
        var test = testService.getTest(id);
        return ResponseEntity.ok(test);
    }
}
