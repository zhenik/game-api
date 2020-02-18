package com.zhenik.odachan.game.api.domain.user;

public enum UserRole {
  ADMIN("ADMIN"),
  USER("USER");

  private final String text;

  UserRole(final String text) {
    this.text = text;
  }

  @Override public String toString() {
    return this.text;
  }
}
