package com.zhenik.odachan.game.api.domain.list;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zhenik.odachan.game.api.domain.BaseMongoEntity;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import io.quarkus.mongodb.panache.MongoEntity;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@MongoEntity(collection = "list")
public class ListQuestions extends BaseMongoEntity {
  private String assignedToEmail;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime assignedDate;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime delivered;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime deadline;
  private List<Question> questions;
  private ListState state;

  public static ListQuestions of(CreateListCommand createListCommand) {
    ListQuestions listQuestions = new ListQuestions();

    if (Objects.nonNull(createListCommand.getAssignedToEmail()) && !createListCommand.getAssignedToEmail().isEmpty()) {
      listQuestions.setAssignedToEmail(createListCommand.getAssignedToEmail());
      listQuestions.setAssignedDate(LocalDateTime.now());
      listQuestions.setState(ListState.WORK_IN_PROGRESS);
    } else {
      listQuestions.setAssignedToEmail(null);
      listQuestions.setAssignedDate(null);
      listQuestions.setState(ListState.NOT_ASSIGNED);
    }
    listQuestions.setDelivered(null);
    listQuestions.setDeadline(Objects.nonNull(createListCommand.getDeadline())
        ? fromMilliseconds(createListCommand.getDeadline())
        : null);

    listQuestions.setQuestions(
        createListCommand.getQuestions().stream()
            .map(
                question -> {
                  final Question q = new Question();
                  q.setId(question.getId());
                  q.setText(question.getText());
                  q.setAnswer(question.getAnswer());
                  q.setComment(question.getComment());
                  q.setScore(Optional.ofNullable(question.getScore()).orElse(0));
                  return q;
                })
            .collect(Collectors.toList())
    );

    listQuestions.setCreatedAt(LocalDateTime.now());
    listQuestions.setUpdatedAt(LocalDateTime.now());
    return listQuestions;
  }

  public String getAssignedToEmail() { return assignedToEmail; }
  public void setAssignedToEmail(String assignedToEmail) { this.assignedToEmail = assignedToEmail; }
  public LocalDateTime getAssignedDate() { return assignedDate; }
  public void setAssignedDate(LocalDateTime assignedDate) { this.assignedDate = assignedDate; }
  public LocalDateTime getDelivered() { return delivered; }
  public void setDelivered(LocalDateTime delivered) { this.delivered = delivered; }
  public LocalDateTime getDeadline() { return deadline; }
  public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
  public List<Question> getQuestions() { return questions; }
  public void setQuestions(List<Question> questions) { this.questions = questions; }
  public ListState getState() { return state; }
  public void setState(ListState state) { this.state = state; }

  private static LocalDateTime fromMilliseconds(Long milliseconds) {
    return
        LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds),
            TimeZone.getDefault().toZoneId());
  }

  private static LocalDateTime fromEpoch(Long epochSeconds) {
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSeconds),
        TimeZone.getDefault().toZoneId());
  }
}
