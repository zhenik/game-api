package com.zhenik.odachan.game.api.dto;

import com.zhenik.odachan.game.api.domain.list.AnswerState;
import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.list.Question;
import com.zhenik.odachan.game.api.domain.list.Segment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserAnswerGroupedStatistics {
  private Map<AnswerState, Integer> group;

  private UserAnswerGroupedStatistics() {
    //this.group = initGroup();
  }

  public UserAnswerGroupedStatistics(List<ListQuestions> lists) {
    this.group = calculateAnswerGroupedStatistics(lists);
  }

  private Map<AnswerState, Integer> initGroup() {
    return new HashMap<AnswerState, Integer>(){{
      put(AnswerState.YES, 0);
      put(AnswerState.NO, 0);
      put(AnswerState.IRRELEVANT, 0);
      put(AnswerState.NONE, 0);
    }};
  }

  public Map<AnswerState, Integer> calculateAnswerGroupedStatistics(List<ListQuestions> lists) {
    final Map<AnswerState, Integer> answerStateGrouped = initGroup();
    // flatten questions
    for (ListQuestions l : lists) {
      for (Segment s : l.getSegments()) {
        if (s.getQuestions() != null){
          for (Question q : s.getQuestions()) {
            answerStateGrouped.put(q.getAnswer(), answerStateGrouped.get(q.getAnswer()) + 1);
          }
        }
      }
    }
    return answerStateGrouped;
  }

  public Map<AnswerState, Integer> getGroup() { return group; }
  public void setGroup(Map<AnswerState, Integer> group) { this.group = group; }
}
