package com.zhenik.odachan.game.api.dto;

import com.zhenik.odachan.game.api.domain.list.AnswerState;
import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAnswerGroupedStatisticsTest {

  @Test
  public void userAllListsAnswerAnalytics_FiftyPercentScore_Success() {
    // Given
    final List<ListQuestions> lists = Utils.getList_TwoSegmentEach_TwoQuestionsEach();
    final HashMap<AnswerState, Integer> expected = new HashMap<AnswerState, Integer>() {{
      put(AnswerState.YES, 2);
      put(AnswerState.NO, 2);
      put(AnswerState.IRRELEVANT, 2);
      put(AnswerState.NONE, 2);
    }};

    // When
    final UserAnswerGroupedStatistics userAnswerGroupedStatistics =
        new UserAnswerGroupedStatistics(lists);

    // Assert
    assertEquals(expected, userAnswerGroupedStatistics.getGroup());
  }
}
