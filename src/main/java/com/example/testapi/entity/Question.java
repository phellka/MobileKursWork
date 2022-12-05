package com.example.testapi.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question extends AbstractEntity {

    @Column(name = "text")
    private String questionText;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    private Test test;

}
