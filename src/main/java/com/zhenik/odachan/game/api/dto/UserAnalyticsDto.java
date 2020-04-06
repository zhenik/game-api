package com.zhenik.odachan.game.api.dto;

import com.zhenik.odachan.game.api.domain.list.Analytics;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class UserAnalyticsDto {
  private TwoListsFeedbackDto twoListsFeedback;
  private Analytics allListsFeedback;

  public UserAnalyticsDto() { }

  public TwoListsFeedbackDto getTwoListsFeedback() { return twoListsFeedback; }
  public void setTwoListsFeedback(TwoListsFeedbackDto twoListsFeedback) { this.twoListsFeedback = twoListsFeedback; }
  public Analytics getAllListsFeedback() { return allListsFeedback; }
  public void setAllListsFeedback(Analytics allListsFeedback) { this.allListsFeedback = allListsFeedback; }

  @Override public String toString() {
    return "AnalyticsResult{" +
        "twoListsFeedback=" + twoListsFeedback +
        ", allListsFeedback=" + allListsFeedback +
        '}';
  }
}
