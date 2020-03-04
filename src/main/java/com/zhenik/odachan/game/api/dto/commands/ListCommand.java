package com.zhenik.odachan.game.api.dto.commands;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zhenik.odachan.game.api.domain.list.ListState;
import com.zhenik.odachan.game.api.domain.list.Question;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;

public class ListCommand {
  private ObjectId id;
  private String assignedToEmail;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime createdAt;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime updatedAt;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime assignedDate;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime deadline;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime delivered;
  private List<Question> questions;
  private ListState state;

  public ObjectId getId() { return id; }
  public void setId(ObjectId id) { this.id = id; }
  public String getAssignedToEmail() { return assignedToEmail; }
  public void setAssignedToEmail(String assignedToEmail) { this.assignedToEmail = assignedToEmail; }
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
  public LocalDateTime getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
  public LocalDateTime getAssignedDate() { return assignedDate; }
  public void setAssignedDate(LocalDateTime assignedDate) { this.assignedDate = assignedDate; }
  public LocalDateTime getDeadline() { return deadline; }
  public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
  public LocalDateTime getDelivered() { return delivered; }
  public void setDelivered(LocalDateTime delivered) { this.delivered = delivered; }
  public List<Question> getQuestions() { return questions; }
  public void setQuestions(List<Question> questions) { this.questions = questions; }
  public ListState getState() { return state; }
  public void setState(ListState state) { this.state = state; }

  @Override public String toString() {
    return "ListCommand{" +
        "id=" + id +
        ", assignedToEmail='" + assignedToEmail + '\'' +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        ", assignedDate=" + assignedDate +
        ", deadline=" + deadline +
        ", delivered=" + delivered +
        ", questions=" + questions +
        ", state=" + state +
        '}';
  }
}
