package com.zhenik.odachan.game.api.service;

import com.zhenik.odachan.game.api.domain.BaseMongoEntity;
import com.zhenik.odachan.game.api.domain.list.Analytics;
import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.list.ListState;
import com.zhenik.odachan.game.api.domain.list.Question;
import com.zhenik.odachan.game.api.domain.list.Segment;
import com.zhenik.odachan.game.api.dto.EmailPercent;
import com.zhenik.odachan.game.api.dto.TwoListsFeedbackDto;
import com.zhenik.odachan.game.api.dto.UserAnalyticsDto;
import com.zhenik.odachan.game.api.dto.UserAnswerStatistics;
import com.zhenik.odachan.game.api.repository.ListRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AnalyticsService {
  @Inject ListRepository listRepository;

  public UserAnalyticsDto getUserAnalytics(String email) {
    final PanacheQuery<ListQuestions> query =
        ListQuestions.find("assignedToEmail = ?1 and state = ?2", email, ListState.DELIVERED);

    final List<ListQuestions> userLists = query.stream().collect(Collectors.toList());

    if (userLists.size() == 0) {
      return null;
    }

    final TwoListsFeedbackDto twoListsFeedback = buildTwoListAnalytics(userLists);
    final Analytics allListAnalytics = buildAllListAnalytics(userLists);

    final UserAnalyticsDto userAnalyticsDto = new UserAnalyticsDto();
    userAnalyticsDto.setAllListsFeedback(allListAnalytics);
    userAnalyticsDto.setTwoListsFeedback(twoListsFeedback);

    return userAnalyticsDto;
  }

  private TwoListsFeedbackDto buildTwoListAnalytics(List<ListQuestions> userLists) {
    ListQuestions lastDeliveredList =
        userLists.stream()
            .sorted(Comparator.comparing(BaseMongoEntity::getUpdatedAt).reversed())
            .collect(Collectors.toList())
            .get(0);
    final Analytics last = lastDeliveredList.getAnalytics();

    ListQuestions bestDeliveredList =
        userLists.stream()
            .sorted(
                Comparator.comparing((ListQuestions l) -> l.getAnalytics().getScore()).reversed())
            .collect(Collectors.toList())
            .get(0);

    final TwoListsFeedbackDto twoListsFeedbackDto = new TwoListsFeedbackDto();
    twoListsFeedbackDto.setLast(lastDeliveredList.getAnalytics());
    twoListsFeedbackDto.setBest(bestDeliveredList.getAnalytics());
    return twoListsFeedbackDto;
  }

  private Analytics buildAllListAnalytics(List<ListQuestions> userLists) {
    final Analytics analytics = Analytics.empty();
    for (ListQuestions list : userLists) {
      for (Segment segment : list.getSegments()) {
        for (Question question : segment.getQuestions()) {
          analytics.setQuestions(analytics.getQuestions() + 1);
          analytics.setScore(analytics.getScore() + question.getScore());
          analytics.addToGroup(question.getAnswer());
        }
      }
    }
    Float feedback = ((Float.valueOf(analytics.getScore()) / analytics.getQuestions()) * 100);
    analytics.setFeedback(feedback);
    return analytics;
  }

  /**
   * }===|==>---- crutch
   * todo: reuse buildAllListAnalytics()
   * Bad performance relates to database size
   * Contain
   * #4: top10 users based on user's all list answers score
   */
  public List<EmailPercent> getUsersAnalytics() {
    final PanacheQuery<ListQuestions> query = ListQuestions.find("state", ListState.DELIVERED);

    Map<String, List<ListQuestions>> groupedByUser = new HashMap<>();
    Map<String, Float> usersStatistics = new HashMap<>();

    // grouping
    for (ListQuestions l : query.stream().collect(Collectors.toList())) {
      final String email = l.getAssignedToEmail();
      final List<ListQuestions> grouped = groupedByUser.getOrDefault(email, new ArrayList<>());
      grouped.add(l);
      groupedByUser.put(email, grouped);
    }

    // calculating
    for (Map.Entry<String,List<ListQuestions>> entry : groupedByUser.entrySet()) {
      // todo: optimise here
      usersStatistics.put(entry.getKey(), new UserAnswerStatistics(entry.getValue()).getPercent());
    }

    // sort & get 10
    final List<EmailPercent> sortedTopTen = usersStatistics.entrySet()
        .stream()
        .map((entry) -> new EmailPercent(entry.getKey(), entry.getValue()))
        .sorted(Comparator.comparingDouble(EmailPercent::getPercent).reversed())
        .limit(10)
        .collect(Collectors.toList());

    return sortedTopTen;
  }
}
