package com.zhenik.odachan.game.api.dto.commands;

import com.zhenik.odachan.game.api.domain.enums.UserRole;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public class UserSaveCommand {
  @NotBlank private String name;
  @NotNull  private String email;
  @NotNull private UserRole role;
  private ObjectId collectionId;

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public UserRole getRole() { return role; }
  public void setRole(UserRole role) { this.role = role; }
  public ObjectId getCollectionId() { return collectionId; }
  public void setCollectionId(ObjectId collectionId) { this.collectionId = collectionId; }
}
