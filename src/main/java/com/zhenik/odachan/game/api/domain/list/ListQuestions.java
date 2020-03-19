package com.zhenik.odachan.game.api.domain.list;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zhenik.odachan.game.api.domain.BaseMongoEntity;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import com.zhenik.odachan.game.api.dto.commands.UpdateListCommand;
import io.quarkus.mongodb.panache.MongoEntity;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;

@MongoEntity(collection = "list")
public class ListQuestions extends BaseMongoEntity {
  // todo or question: collectionId removed
  //private ObjectId id;
  private String assignedToEmail;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime assignedDate;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime delivered;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime deadline;
  private List<Segment> segments;
  private ListState state;
  private Analytics analytics;

  public ListQuestions() {
    this.segments = new ArrayList<>();
  }

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
        ? createListCommand.getDeadline()
        : null);
    listQuestions.setSegments(segmentsOf(createListCommand));
    //listQuestions.setSegments(createListCommand.getSegments());

    listQuestions.setCreatedAt(LocalDateTime.now());
    listQuestions.setUpdatedAt(LocalDateTime.now());
    listQuestions.setAnalytics(Analytics.empty());
    return listQuestions;
  }

  /** Method is applied only for list creation.
   * Ignoring any answer state, set up to NONE
   * Also setup score of each question to `0`
   * */
  private static List<Segment> segmentsOf(CreateListCommand createListCommand) {
    return createListCommand.getSegments().stream()
        .map(segment -> {
            Segment s = new Segment();
            s.setId(segment.getId());
            s.setTitle(segment.getTitle());
            s.setDescription(segment.getDescription());

            if (segment.getQuestions() == null) {
              s.setQuestions(new ArrayList<>());
            } else {
              s.setQuestions(
                  segment.getQuestions().stream()
                      .map(
                          question -> {
                            final Question q = new Question();
                            q.setId(question.getId());
                            q.setText(question.getText());
                            q.setAnswer(AnswerState.NONE);
                            q.setScore(0);
                            q.setComment(question.getComment());
                            return q;
                          })
                      .collect(Collectors.toList()));
            }
            return s;
          })
        .collect(Collectors.toList());
  }

  public static ListQuestions of(UpdateListCommand updateListCommand) {
    ListQuestions listQuestions = new ListQuestions();

    listQuestions.setAssignedToEmail(updateListCommand.getAssignedToEmail());
    listQuestions.setAssignedDate(updateListCommand.getAssignedDate());
    listQuestions.setState(updateListCommand.getState());
    listQuestions.setDelivered(updateListCommand.getDelivered());

    listQuestions.setDeadline(updateListCommand.getDeadline());
    listQuestions.setSegments(updateListCommand.getSegments());
    listQuestions.setCreatedAt(updateListCommand.getCreatedAt());
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
  public List<Segment> getSegments() { return segments; }
  public void setSegments(List<Segment> segments) { this.segments = segments; }
  public ListState getState() { return state; }
  public void setState(ListState state) { this.state = state; }
  public Analytics getAnalytics() { return analytics; }
  public void setAnalytics(Analytics analytics) { this.analytics = analytics; }

  // todo: rm later
  private static LocalDateTime fromMilliseconds(Long milliseconds) {
    return
        LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds),
            TimeZone.getDefault().toZoneId());
  }

  // todo: rm later
  private static LocalDateTime fromEpoch(Long epochSeconds) {
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSeconds),
        TimeZone.getDefault().toZoneId());
  }

  @Override public String toString() {
    return "ListQuestions{" +
        "assignedToEmail='" + assignedToEmail + '\'' +
        ", assignedDate=" + assignedDate +
        ", delivered=" + delivered +
        ", deadline=" + deadline +
        ", segments=" + segments +
        ", state=" + state +
        ", analytics=" + analytics +
        ", id=" + id +
        '}';
  }
}
