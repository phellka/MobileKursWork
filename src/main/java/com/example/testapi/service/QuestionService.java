package com.example.testapi.service;

import com.example.testapi.dto.AnswerDto;
import com.example.testapi.dto.AnswerResponseDto;
import com.example.testapi.dto.QuestionDto;
import com.example.testapi.dto.QuestionResponseDto;
import com.example.testapi.entity.Answer;
import com.example.testapi.entity.Question;
import com.example.testapi.entity.Test;
import com.example.testapi.exceptions.EntityNotFoundException;
import com.example.testapi.reposity.QuestionRepository;
import com.example.testapi.reposity.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    public QuestionResponseDto getQuestion(Long id) {
        var entity = questionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Question by id not found"));
        return mapToQuestionResponseDto(entity);
    }

    public QuestionDto createQuestion(QuestionDto dto) {
        var test = testRepository.findById(1L).orElseThrow(
                ()-> new IllegalArgumentException("Test for question not found"));

        var question = questionRepository.save(mapToQuestion(dto, test));
        return mapQuestionDto(question);
    }

    public QuestionDto putQuestion(QuestionDto dto, Long questionId) {
        var test = testRepository.findById(1L).orElseThrow(
                ()-> new IllegalArgumentException("Test for question not found"));

        var question = questionRepository.findById(questionId)
                .orElseThrow(
                        () -> new IllegalArgumentException(String.format("Question with id = %s not found", questionId)));

        question.setTest(test);
        question.setQuestionText(dto.getText());
        question.setAnswers(dto.getAnswers().stream().map(answerDto -> mapToAnswer(answerDto, question)).collect(Collectors.toList()));

        return mapQuestionDto(questionRepository.save(question));
    }

    private Question mapToQuestion(QuestionDto dto, Test test) {
        var entity = new Question();
        entity.setTest(test);
        entity.setQuestionText(dto.getText());
        entity.setAnswers(dto.getAnswers().stream().map(answerDto -> mapToAnswer(answerDto, entity)).collect(Collectors.toList()));
        return entity;
    }

    private Answer mapToAnswer(AnswerDto dto, Question question) {
        var answerEntity = new Answer();
        answerEntity.setQuestion(question);
        answerEntity.setAnswerText(dto.getText());
        answerEntity.setIsCorrect(dto.getIsCorrect());
        return answerEntity;
    }

    private Answer mapToAnswerWithoutCreation(AnswerDto dto, Question question, Answer entity) {
        var answerEntity = new Answer();
        answerEntity.setQuestion(question);
        answerEntity.setAnswerText(dto.getText());
        answerEntity.setIsCorrect(dto.getIsCorrect());
        return answerEntity;
    }


    private QuestionDto mapQuestionDto(Question entity) {
        return QuestionDto.builder()
                .text(entity.getQuestionText())
                .answers(entity.getAnswers().stream().map(this::mapToAnswerDto).collect(Collectors.toList()))
                .build();
    }

    private AnswerDto mapToAnswerDto(Answer entity) {
        return AnswerDto.builder()
                .text(entity.getAnswerText())
                .isCorrect(entity.getIsCorrect())
                .build();
    }

    private QuestionResponseDto mapToQuestionResponseDto(Question entity) {
        return QuestionResponseDto.builder()
                .text(entity.getQuestionText())
                .answers(entity.getAnswers().stream().map(this::mapToAnswerResponseDto).collect(Collectors.toList()))
                .build();
    }

    private AnswerResponseDto mapToAnswerResponseDto(Answer answer) {
        return AnswerResponseDto.builder()
                .text(answer.getAnswerText())
                .build();
    }


}
