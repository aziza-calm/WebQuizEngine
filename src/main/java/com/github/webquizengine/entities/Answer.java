package com.github.webquizengine.entities;

import java.util.List;

public class Answer {
//    @NotNull
    private List<Integer> answer;

    public Answer() {
        this.answer = answer == null ? List.of() : answer;
    }

    public Answer(List<Integer> answer) {
        this.answer = answer;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
