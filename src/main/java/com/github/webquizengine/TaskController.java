package com.github.webquizengine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.webquizengine.entities.Quiz;
import com.github.webquizengine.entities.QuizAnswerFeedback;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@Validated
@RestController
public class TaskController {
    ObjectMapper mapper = new ObjectMapper();
    Map<Integer, Quiz> quizzes = new HashMap<>();
    private final QuizAnswerFeedback rightAnswer = new QuizAnswerFeedback(true, "Congratulations, you're right!");
    private final QuizAnswerFeedback wrongAnswer = new QuizAnswerFeedback(false, "Wrong answer! Please, try again.");

    @PostMapping(value = "/api/quizzes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Quiz postQuiz(@RequestBody @Valid Quiz quiz) {
        quiz.setId(quizzes.size());
        quizzes.put(quiz.getId(), quiz);
        return quiz;
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuizWithId(@PathVariable int id) {
        if (id >= quizzes.size()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The quiz not found");
        return quizzes.get(id);
    }

    @GetMapping("/api/quizzes")
    public List<Quiz> getAllQuizzes() {
        return new ArrayList<>(quizzes.values());
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public QuizAnswerFeedback solveQuiz(@RequestBody Quiz answerQuiz, @PathVariable int id) {
        if (answerQuiz == null && quizzes.get(id).getAnswer().isEmpty()) return rightAnswer;
        if (Objects.equals(quizzes.get(id).getAnswer(), answerQuiz.getAnswer())) {
            return rightAnswer;
        }
        if (!Objects.equals(quizzes.get(id).getAnswer(), answerQuiz.getAnswer())){
            return wrongAnswer;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The quiz not found");
    }
}
