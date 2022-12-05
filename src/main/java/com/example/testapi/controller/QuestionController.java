package com.example.testapi.controller;

import com.example.testapi.dto.QuestionDto;
import com.example.testapi.dto.QuestionResponseDto;
import com.example.testapi.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("{id}")
    public ResponseEntity<QuestionResponseDto> getQuestion(@PathVariable Long id) {
        var question = questionService.getQuestion(id);
        return ResponseEntity.ok(question);
    }

    @PostMapping()
    public ResponseEntity<QuestionDto> postQuestion(@RequestBody QuestionDto dto) {
        return ResponseEntity.ok(questionService.createQuestion(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<QuestionDto> putQuestion(@RequestBody QuestionDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(questionService.putQuestion(dto, id));
    }


}
