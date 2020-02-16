//package com.zhenik.odachan.game.api.dto.query;
//
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import com.zhenik.odachan.game.api.domain.list.ListQuestions;
//import com.zhenik.odachan.game.api.domain.list.ListState;
//import io.quarkus.mongodb.panache.ProjectionFor;
//import java.time.LocalDateTime;
//import java.util.List;
//import org.bson.codecs.pojo.annotations.BsonProperty;
//
//@ProjectionFor(ListQuestions.class)
//public class ListQuestionsRow {
//  @BsonProperty("assigned_to_email")
//  private String assignedToEmail;
//
//  @JsonSerialize(using = ToStringSerializer.class)
//  @BsonProperty("assigned_date")
//  private LocalDateTime assignedDate;
//
//  @JsonSerialize(using = ToStringSerializer.class)
//  private LocalDateTime delivered;
//
//  @JsonSerialize(using = ToStringSerializer.class)
//  private LocalDateTime deadline;
//
//  private List<ListQuestions> questions;
//  private ListState state;
//}
