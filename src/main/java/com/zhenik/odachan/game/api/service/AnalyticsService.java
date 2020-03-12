package com.zhenik.odachan.game.api.service;

import com.zhenik.odachan.game.api.domain.BaseMongoEntity;
import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.list.ListState;
import com.zhenik.odachan.game.api.dto.AnalyticsResult;
import com.zhenik.odachan.game.api.dto.EmailPercent;
import com.zhenik.odachan.game.api.dto.UserAnswerGroupedStatistics;
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

  /**
   * Contain
   * #1: userAllListsAnswersPercent
   * #2: userRecentListAnswersPercent
   * #3: userAllListGroupedAnswersCount
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

    UserAnswerStatistics userAnalyticsAllListAnswers = getUserAnswerStatistics(userLists);
    UserAnswerStatistics userAnalyticsRecentListAnswers =
        getUserAnswerStatistics(lastDeliveredList);
    UserAnswerGroupedStatistics userAnswerGroupedStatistics =
        getUserAnswerGroupedStatistics(userLists);

    return new AnalyticsResult(
        userAnalyticsAllListAnswers, userAnalyticsRecentListAnswers, userAnswerGroupedStatistics);
  }

  /**
   * todo:  }===|==>---- crutch
   * todo: test it
   * Bad performance relates to database size
   *
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
      usersStatistics.put(entry.getKey(), new UserAnswerStatistics(entry.getValue()).getScorePercentage());
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


  // #1 statistics: user's all list answers score `%`
  private UserAnswerStatistics getUserAnswerStatistics(List<ListQuestions> userQuestionLists) {
    return new UserAnswerStatistics(userQuestionLists);
  }
  // #2 statistics: user's all list answers score `%`
  private UserAnswerStatistics getUserAnswerStatistics(ListQuestions lastDeliveredList) {
    return new UserAnswerStatistics(lastDeliveredList);
  }

  // #3 statistics: user's all list answer states `count` by group
  private UserAnswerGroupedStatistics getUserAnswerGroupedStatistics(List<ListQuestions> userQuestionLists) {
    return new UserAnswerGroupedStatistics(userQuestionLists);
  }

  //public static void main(String[] args) {
  //
  //  final ListQuestions listQuestions1 = new ListQuestions();
  //  final ListQuestions listQuestions2 = new ListQuestions();
  //  final Segment segment1 = new Segment();
  //  final Segment segment2 = new Segment();
  //
  //  final Question q1 = new Question();
  //  final Question q2 = new Question();
  //  q1.setScore(1);
  //  q2.setScore(0);
  //
  //  segment1.setQuestions(Arrays.asList(q2, q1, q1)); // 3
  //  segment2.setQuestions(Arrays.asList(q2, q1)); // 2
  //
  //  listQuestions1.setSegments(Arrays.asList(segment1, segment2));
  //  listQuestions1.setAssignedToEmail("zhenik");
  //
  //  listQuestions2.setSegments(Arrays.asList(segment1, segment2, segment2));
  //  listQuestions2.setAssignedToEmail("chan");
  //  List<ListQuestions> lists = Arrays.asList(listQuestions1, listQuestions1, listQuestions2);
  //
  //
  //  Map<String, List<ListQuestions>> groupedByUser = new HashMap<>();
  //  Map<String, Float> usersStatistics = new HashMap<>();
  //
  //  for (ListQuestions l : lists) {
  //    final String email = l.getAssignedToEmail();
  //    final List<ListQuestions> grouped = groupedByUser.getOrDefault(email, new ArrayList<>());
  //    grouped.add(l);
  //    groupedByUser.put(email, grouped);
  //  }
  //
  //  //System.out.println(groupedByUser);
  //
  //  for (Map.Entry<String,List<ListQuestions>> entry : groupedByUser.entrySet()) {
  //    usersStatistics.put(entry.getKey(), new UserAnswerStatistics(entry.getValue()).getScorePercentage());
  //  }
  //
  //  final Map<String, Float> collect = usersStatistics.entrySet().stream()
  //      .sorted(Map.Entry.<String, Float>comparingByValue().reversed())
  //      .limit(10)
  //      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  //
  //  System.out.println(collect);
  //}
}
