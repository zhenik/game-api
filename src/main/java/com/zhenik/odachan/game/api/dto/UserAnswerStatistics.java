package com.zhenik.odachan.game.api.dto;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.list.Question;
import com.zhenik.odachan.game.api.domain.list.Segment;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.List;

/**
 * #1 USER | Local | Calculation
 * score
 *   - min is 0
 *   - max is 1
 * */
@RegisterForReflection
public class UserAnswerStatistics {
  private Float percent;

  public UserAnswerStatistics(ListQuestions list) {
    this.percent = calculateAnswerStatistics(list);
  }

  public UserAnswerStatistics(List<ListQuestions> lists) {
    this.percent = calculateAnswerStatistics(lists);
  }

  private Float calculateAnswerStatistics(List<ListQuestions> list) {
    Float questionsCount = 0.0F;
    Float totalScore = 0.0F;

    for (ListQuestions l : list) {
      for (Segment s : l.getSegments()) {
        if (s.getQuestions() != null){
          questionsCount += (float) s.getQuestions().size();
          for (Question q : s.getQuestions()) {
            totalScore += Float.valueOf(q.getScore());
          }
        }
      }
    }

    Float percentage = ((totalScore/questionsCount) * 100);
    return percentage;
  }

  private Float calculateAnswerStatistics(ListQuestions list) {
    Float questionsCount = 0.0F;
    Float totalScore = 0.0F;

    if (list != null) {
      for (Segment s : list.getSegments()) {
        if (s.getQuestions() != null) {
          questionsCount += (float) s.getQuestions().size();
          for (Question q : s.getQuestions()) {
            totalScore += Float.valueOf(q.getScore());
          }
        }
      }
    }

    Float percentage = ((totalScore/questionsCount) * 100);
    return percentage;
  }

  public Float getPercent() { return percent; }
  public void setPercent(Float percent) { this.percent = percent; }

  @Override public String toString() {
    return "UserAnalyticsAnswerRate{" +
        "percent=" + percent +
        '}';
  }
}
