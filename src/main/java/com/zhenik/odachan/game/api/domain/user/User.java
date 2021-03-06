package com.zhenik.odachan.game.api.domain.user;

import com.zhenik.odachan.game.api.domain.BaseMongoEntity;
import com.zhenik.odachan.game.api.dto.commands.UserSaveCommand;
import io.quarkus.mongodb.panache.MongoEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;

@MongoEntity(collection = "user")
public class User extends BaseMongoEntity {
  private String name;
  @NotNull private String email;
  @NotNull private UserRole role;
  private ObjectId collectionId;

  public static User of(UserSaveCommand userSaveCommand){
    User user = new User();
    user.setCollectionId(Objects.nonNull(userSaveCommand.getCollectionId())
        ? new ObjectId(String.valueOf(userSaveCommand.getCollectionId()))
        : null);
    user.setName(userSaveCommand.getName());
    user.setEmail(userSaveCommand.getEmail());
    user.setRole(userSaveCommand.getRole());
    user.setUpdatedAt(LocalDateTime.now());
    user.setCreatedAt(LocalDateTime.now());
    return user;
  }

  public static Optional<User> findByEmail(String email) {
    return find("email", email).firstResultOptional();
  }

  public static List<User> findAllByRole(UserRole role) {
    return find("role", role.name()).list();
  }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public UserRole getRole() { return role; }
  public void setRole(UserRole role) { this.role = role; }
  public ObjectId getCollectionId() { return collectionId; }
  public void setCollectionId(ObjectId collectionId) { this.collectionId = collectionId; }
}
