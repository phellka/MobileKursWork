package com.example.testapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
@Getter
@Setter
public class Answer extends AbstractEntity {

    private String answerText;
    private Boolean isCorrect;

    @ManyToOne(cascade = CascadeType.ALL)
    private Question question;

}
