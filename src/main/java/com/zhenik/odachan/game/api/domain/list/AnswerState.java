package com.zhenik.odachan.game.api.domain.list;

public enum AnswerState {
  YES("YES"),
  NO("NO"),
  IRRELEVANT("IRRELEVANT"),
  NONE("NONE");

  private final String text;

  AnswerState(final String text) {
    this.text = text;
  }

  @Override public String toString() {
    return this.text;
  }
}
