package com.zhenik.odachan.game.api.dto;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAnalyticsAnswerStatisticsTest {

  @Test
  public void createUserAnalyticsAnswerStatistics_FiftyPercentScore_Success() {
    // Given
    final List<ListQuestions> lists = Utils.getCollectionOfListQuestions();

    // When
    final UserAnalyticsAnswerStatistics userAnalyticsAnswerStatistics =
        new UserAnalyticsAnswerStatistics(lists);

    // Assert
    assertEquals(50, userAnalyticsAnswerStatistics.getScorePercentage());
  }

  @Test
  public void createUserAnalyticsAnswerStatistics_SeventyPercentScore_Success() {
    // Given
    final List<ListQuestions> lists = Utils.getSeventyPercentScore();

    // When
    final UserAnalyticsAnswerStatistics userAnalyticsAnswerStatistics =
        new UserAnalyticsAnswerStatistics(lists);

    // Assert
    assertEquals(70, userAnalyticsAnswerStatistics.getScorePercentage());
  }
  //getSeventyPercentScore

}
