package com.zhenik.odachan.game.api.dto;

public class AnalyticsResult {

  private UserAnswerStatistics userTotalDeliveredAnswersFeedback;
  private UserAnswerStatistics userLastListDeliveredAnswersFeedback;
  private UserAnswerGroupedStatistics userListsGroupedAnswersCount;

  public AnalyticsResult() {}

  public AnalyticsResult(
      UserAnswerStatistics userTotalDeliveredAnswersFeedback,
      UserAnswerStatistics userLastListDeliveredAnswersFeedback,
      UserAnswerGroupedStatistics userListsGroupedAnswersCount) {
    this.userTotalDeliveredAnswersFeedback = userTotalDeliveredAnswersFeedback;
    this.userLastListDeliveredAnswersFeedback = userLastListDeliveredAnswersFeedback;
    this.userListsGroupedAnswersCount = userListsGroupedAnswersCount;
  }

  public UserAnswerStatistics getUserTotalDeliveredAnswersFeedback() {
    return userTotalDeliveredAnswersFeedback;
  }
  public void setUserTotalDeliveredAnswersFeedback(
      UserAnswerStatistics userTotalDeliveredAnswersFeedback) {
    this.userTotalDeliveredAnswersFeedback = userTotalDeliveredAnswersFeedback;
  }
  public UserAnswerStatistics getUserLastListDeliveredAnswersFeedback() {
    return userLastListDeliveredAnswersFeedback;
  }
  public void setUserLastListDeliveredAnswersFeedback(
      UserAnswerStatistics userLastListDeliveredAnswersFeedback) {
    this.userLastListDeliveredAnswersFeedback = userLastListDeliveredAnswersFeedback;
  }
  public UserAnswerGroupedStatistics getUserListsGroupedAnswersCount() {
    return userListsGroupedAnswersCount;
  }
  public void setUserListsGroupedAnswersCount(
      UserAnswerGroupedStatistics userListsGroupedAnswersCount) {
    this.userListsGroupedAnswersCount = userListsGroupedAnswersCount;
  }
}
