package com.zhenik.odachan.game.api.domain.list;

public class Question {
  private Integer id;
  private String text;
  private String answer;
  private String comment;
  private Integer score;

  public Question() { }

  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }
  public String getText() { return text; }
  public void setText(String text) { this.text = text; }
  public String getAnswer() { return answer; }
  public void setAnswer(String answer) { this.answer = answer; }
  public String getComment() { return comment; }
  public void setComment(String comment) { this.comment = comment; }
  public Integer getScore() { return score; }
  public void setScore(Integer score) { this.score = score; }

  @Override public String toString() {
    return "Question{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", answer='" + answer + '\'' +
        ", comment='" + comment + '\'' +
        ", score=" + score +
        '}';
  }
}

