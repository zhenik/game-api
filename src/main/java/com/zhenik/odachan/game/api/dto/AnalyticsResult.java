package com.zhenik.odachan.game.api.dto;

public class AnalyticsResult {

  private UserAnswerStatistics userAllListsAnswersPercent;
  private UserAnswerStatistics userRecentListAnswersPercent;
  private UserAnswerGroupedStatistics userAllListGroupedAnswersCount;

  public AnalyticsResult() {}

  public AnalyticsResult(
      UserAnswerStatistics userAllListsAnswersPercent,
      UserAnswerStatistics userRecentListAnswersPercent,
      UserAnswerGroupedStatistics userAllListGroupedAnswersCount) {
    this.userAllListsAnswersPercent = userAllListsAnswersPercent;
    this.userRecentListAnswersPercent = userRecentListAnswersPercent;
    this.userAllListGroupedAnswersCount = userAllListGroupedAnswersCount;
  }

  public UserAnswerStatistics getUserAllListsAnswersPercent() {
    return userAllListsAnswersPercent;
  }
  public void setUserAllListsAnswersPercent(
      UserAnswerStatistics userAllListsAnswersPercent) {
    this.userAllListsAnswersPercent = userAllListsAnswersPercent;
  }
  public UserAnswerStatistics getUserRecentListAnswersPercent() {
    return userRecentListAnswersPercent;
  }
  public void setUserRecentListAnswersPercent(
      UserAnswerStatistics userRecentListAnswersPercent) {
    this.userRecentListAnswersPercent = userRecentListAnswersPercent;
  }
  public UserAnswerGroupedStatistics getUserAllListGroupedAnswersCount() {
    return userAllListGroupedAnswersCount;
  }
  public void setUserAllListGroupedAnswersCount(
      UserAnswerGroupedStatistics userAllListGroupedAnswersCount) {
    this.userAllListGroupedAnswersCount = userAllListGroupedAnswersCount;
  }
}
