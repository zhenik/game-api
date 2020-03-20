package com.zhenik.odachan.game.api.dto.commands;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhenik.odachan.game.api.domain.list.Analytics;

public class UpdateListCommand extends ListCommand {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Analytics analytics;

  public Analytics getAnalytics() { return analytics; }
  public void setAnalytics(Analytics analytics) { this.analytics = analytics; }
}
