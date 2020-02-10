package com.zhenik.odachan.game.api.dto.commands;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zhenik.odachan.game.api.domain.list.ListState;
import com.zhenik.odachan.game.api.domain.list.Question;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class CreateListCommand {
  private ObjectId collectionId;
  //@BsonProperty("assigned_to_email")
  private String assignedToEmail;
  //@JsonSerialize(using = ToStringSerializer.class)
  //@BsonProperty("assigned_date")
  //private LocalDateTime assignedDate;
  //@JsonSerialize(using = ToStringSerializer.class)
  //private LocalDateTime delivered;
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDateTime deadline;
  private List<Question> questions;
  private ListState state;

  public ObjectId getCollectionId() { return collectionId; }
  public String getAssignedToEmail() { return assignedToEmail; }
  //public LocalDateTime getAssignedDate() { return assignedDate; }
  //public LocalDateTime getDelivered() { return delivered; }
  public LocalDateTime getDeadline() { return deadline; }
  public List<Question> getQuestions() { return questions; }
  public ListState getState() { return state; }

  public void setCollectionId(ObjectId collectionId) { this.collectionId = collectionId; }
  public void setAssignedToEmail(String assignedToEmail) { this.assignedToEmail = assignedToEmail; }
  //public void setAssignedDate(LocalDateTime assignedDate) { this.assignedDate = assignedDate; }
  //public void setDelivered(LocalDateTime delivered) { this.delivered = delivered; }
  public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
  public void setQuestions(List<Question> questions) { this.questions = questions; }
  public void setState(ListState state) { this.state = state; }

  @Override public String toString() {
    return "CreateListCommand{" +
        "collectionId=" + collectionId +
        ", assignedToEmail='" + assignedToEmail + '\'' +
        ", deadline=" + deadline +
        ", questions=" + questions +
        ", state=" + state +
        '}';
  }
}
