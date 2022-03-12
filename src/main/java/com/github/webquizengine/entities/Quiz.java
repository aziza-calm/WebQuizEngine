package com.github.webquizengine.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Quiz {
    @Id
    Integer id;

    @NotNull(message = "The title must not be null")
    @NotBlank(message = "The title field is mandatory")
    String title;

    @NotNull(message = "The text field must not be null")
    @NotBlank(message = "The text field is mandatory")
    String text;

    @ElementCollection
    @NotNull
    @Size(min = 2)
    List<String> options;

    @ElementCollection
    List<Integer> answer;

    public Quiz() {
        this.answer = answer == null ? List.of() : answer;
    }

    @JsonProperty
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public List<Integer> getAnswer() {
        return answer;
    }

    @JsonProperty
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    @JsonProperty
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    @JsonProperty
    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    @JsonProperty
    public void setOptions(List<String> options) {
        this.options = options;
    }

}
