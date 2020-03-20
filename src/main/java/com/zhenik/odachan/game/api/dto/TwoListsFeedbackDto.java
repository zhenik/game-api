package com.zhenik.odachan.game.api.dto;

import com.zhenik.odachan.game.api.domain.list.Analytics;

public class TwoListsFeedbackDto {
  private Analytics last;
  private Analytics best;

  public TwoListsFeedbackDto() { }

  public Analytics getLast() { return last; }
  public void setLast(Analytics last) { this.last = last; }
  public Analytics getBest() { return best; }
  public void setBest(Analytics best) { this.best = best; }

  @Override public String toString() {
    return "TwoListsFeedback{" +
        "last=" + last +
        ", best=" + best +
        '}';
  }
}
