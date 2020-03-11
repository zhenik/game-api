package com.zhenik.odachan.game.api.dto;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.list.Question;
import com.zhenik.odachan.game.api.domain.list.Segment;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * #1 USER | Local | Calculation
 * score
 *   - min is 0
 *   - max is 1
 * */
public class UserAnalyticsAnswerStatistics {
  private Float scorePercentage;

  public UserAnalyticsAnswerStatistics(List<ListQuestions> list) {
    this.scorePercentage = calculateAnswerStatistics(list);
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

  public Float getScorePercentage() { return scorePercentage; }
  public void setScorePercentage(Float scorePercentage) { this.scorePercentage = scorePercentage; }

  @Override public String toString() {
    return "UserAnalyticsAnswerRate{" +
        "scorePercentage=" + scorePercentage +
        '}';
  }
}
