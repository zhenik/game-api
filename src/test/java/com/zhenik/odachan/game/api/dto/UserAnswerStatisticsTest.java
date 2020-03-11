package com.zhenik.odachan.game.api.dto;

import com.zhenik.odachan.game.api.domain.BaseMongoEntity;
import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAnswerStatisticsTest {

  @Test
  public void userAllListsAnswerAnalytics_FiftyPercentScore_Success() {
    // Given
    final List<ListQuestions> lists = Utils.getCollectionOfListQuestions();

    // When
    final UserAnswerStatistics userAnswerStatistics =
        new UserAnswerStatistics(lists);

    // Assert
    assertEquals(50, userAnswerStatistics.getScorePercentage());
  }

  @Test
  public void userAllListsAnswerAnalytics_SeventyPercentScore_Success() {
    // Given
    final List<ListQuestions> lists = Utils.getSeventyPercentScore();

    // When
    final UserAnswerStatistics userAnswerStatistics =
        new UserAnswerStatistics(lists);

    // Assert
    assertEquals(70, userAnswerStatistics.getScorePercentage());
  }

  @Test
  public void userOneListAnswerAnalytics_SeventyPercentScore_Success() {
    // Given
    final ListQuestions allCorrect = Utils.getList_TwoSegments_SevenQuestions_AllCorrect();
    final ListQuestions allIncorrect = Utils.getList_OneSegment_ThreeQuestions_AllIncorrect();

    // When
    final UserAnswerStatistics hundredPercents = new UserAnswerStatistics(allCorrect);
    final UserAnswerStatistics zeroPercents = new UserAnswerStatistics(allIncorrect);
    UserAnswerStatistics nothing = new UserAnswerStatistics(new ListQuestions());

    // Assert
    assertEquals(100, hundredPercents.getScorePercentage());
    assertEquals(0, zeroPercents.getScorePercentage());
  }

  //@Test
  //public void checkCommon() {
  //  final List<ListQuestions> collected = new ArrayList<ListQuestions>().stream()
  //      .sorted(Comparator.comparing(BaseMongoEntity::getUpdatedAt).reversed())
  //      .collect(Collectors.toList());
  //}

}
