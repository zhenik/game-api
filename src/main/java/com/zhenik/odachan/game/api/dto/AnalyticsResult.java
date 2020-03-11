package com.zhenik.odachan.game.api.dto;

public class AnalyticsResult {

  private UserAnswerStatistics userAllListsAnswersPercent;
  private UserAnswerStatistics userRecentListAnswersPercent;

  public AnalyticsResult() {}

  public AnalyticsResult(
      UserAnswerStatistics userAllListsAnswersPercent,
      UserAnswerStatistics userRecentListAnswersPercent) {
    this.userAllListsAnswersPercent = userAllListsAnswersPercent;
    this.userRecentListAnswersPercent = userRecentListAnswersPercent;
  }

  public UserAnswerStatistics getUserAllListsAnswersPercent() {
    return userAllListsAnswersPercent;
  }

  public void setUserAllListsAnswersPercent(UserAnswerStatistics userAllListsAnswersPercent) {
    this.userAllListsAnswersPercent = userAllListsAnswersPercent;
  }

  public UserAnswerStatistics getUserRecentListAnswersPercent() {
    return userRecentListAnswersPercent;
  }

  public void setUserRecentListAnswersPercent(UserAnswerStatistics userRecentListAnswersPercent) {
    this.userRecentListAnswersPercent = userRecentListAnswersPercent;
  }
}
