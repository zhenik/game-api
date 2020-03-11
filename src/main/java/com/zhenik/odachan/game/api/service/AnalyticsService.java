package com.zhenik.odachan.game.api.service;

import com.zhenik.odachan.game.api.domain.BaseMongoEntity;
import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.list.ListState;
import com.zhenik.odachan.game.api.dto.AnalyticsResult;
import com.zhenik.odachan.game.api.dto.UserAnswerStatistics;
import com.zhenik.odachan.game.api.repository.ListRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AnalyticsService {
  @Inject ListRepository listRepository;

  /**
   * Contain
   * #1: userAnalyticsAllListAnswers
   * #2: userAnalyticsLastDeliveredListAnswers
   */
  public AnalyticsResult getUserAnalytics(String email) {
    final PanacheQuery<ListQuestions> query =
        ListQuestions.find("assignedToEmail = ?1 and state = ?2", email, ListState.DELIVERED);

    final List<ListQuestions> userLists =
        query.stream()
            .sorted(Comparator.comparing(BaseMongoEntity::getUpdatedAt).reversed())
            .collect(Collectors.toList());

    if (userLists.size() == 0) {
      return null;
    }

    ListQuestions lastDeliveredList = userLists.get(0);

    UserAnswerStatistics userAnalyticsAllListAnswers = getUserAnalyticsAnswerStatistics(userLists);
    UserAnswerStatistics userAnalyticsRecentListAnswers =
        getUserAnalyticsAnswerStatistics(lastDeliveredList);

    return new AnalyticsResult(userAnalyticsAllListAnswers, userAnalyticsRecentListAnswers);
  }

  private UserAnswerStatistics getUserAnalyticsAnswerStatistics(
      List<ListQuestions> userQuestionLists) {
    return new UserAnswerStatistics(userQuestionLists);
  }

  private UserAnswerStatistics getUserAnalyticsAnswerStatistics(ListQuestions lastDeliveredList) {
    return new UserAnswerStatistics(lastDeliveredList);
  }
}
