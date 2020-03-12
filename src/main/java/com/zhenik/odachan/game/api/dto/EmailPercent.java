package com.zhenik.odachan.game.api.dto;

public class EmailPercent {
  private String email;
  private Float percent;

  public EmailPercent(String email, Float percent) {
    this.email = email;
    this.percent = percent;
  }
  public EmailPercent() { }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public Float getPercent() { return percent; }
  public void setPercent(Float percent) { this.percent = percent; }

  @Override public String toString() {
    return "EmailPercent{" +
        "email='" + email + '\'' +
        ", percent=" + percent +
        '}';
  }
}
