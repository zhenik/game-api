package com.zhenik.odachan.game.api.domain.list;

import java.util.List;

public class Segment {
  private Integer id;
  private String title;
  private String description;
  private List<Question> questions;

  public Segment() { }

  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public List<Question> getQuestions() { return questions; }
  public void setQuestions(List<Question> questions) { this.questions = questions; }

  @Override public String toString() {
    return "Segment{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", questions=" + questions +
        '}';
  }
}
