package com.zhenik.odachan.game.api.domain.list;

import java.util.HashMap;
import java.util.Map;

//"analytics": {
  //    "questions": 10,
  //    "score": 5,
  //    "feedback": 50.0,
  //    "group": {
  //      "IRRELEVANT": 2,
  //      "NO": 1,
  //      "YES": 3,
  //      "NONE": 1
  //    }
  //}
// will be calculated only when list change status "UNDER_REVIEW" to "DELIVERED"
public class Analytics {
  private Integer questions;
  private Integer score;
  private Float feedback;
  private Map<String, Integer> group;

  public Analytics() { }

  public static Analytics empty() {
    final Analytics analytics = new Analytics();
    analytics.setQuestions(0);
    analytics.setScore(0);
    analytics.setFeedback((float) 0.0);
    analytics.setGroup(emptyGroup());
    return analytics;
  }

  public static Map<String, Integer> emptyGroup() {
    return new HashMap<String, Integer>(){{
      put(AnswerState.IRRELEVANT.toString(), 0);
      put(AnswerState.NONE.toString(), 0);
      put(AnswerState.YES.toString(), 0);
      put(AnswerState.NO.toString(), 0);
    }};
  }

  public Integer getQuestions() { return questions; }
  public void setQuestions(Integer questions) { this.questions = questions; }
  public Integer getScore() { return score; }
  public void setScore(Integer score) { this.score = score; }
  public Float getFeedback() { return feedback; }
  public void setFeedback(Float feedback) { this.feedback = feedback; }
  public Map<String, Integer> getGroup() { return group; }
  public void setGroup(Map<String, Integer> group) { this.group = group; }

  @Override public String toString() {
    return "Analytics{" +
        "questions=" + questions +
        ", score=" + score +
        ", feedback=" + feedback +
        ", group=" + group +
        '}';
  }
}
