package com.zhenik.odachan.game.api.repository;

import com.zhenik.odachan.game.api.domain.user.User;
import com.zhenik.odachan.game.api.domain.user.UserRole;
import com.zhenik.odachan.game.api.dto.commands.UserSaveCommand;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {

  public User save(UserSaveCommand userSaveCommand) {
    Optional<User> conflict = User.findByEmail(userSaveCommand.getEmail());
    if (conflict.isPresent()) {
      return null;
    } else {
      User user = User.of(userSaveCommand);
      user.persist();
      return user;
    }
  }

  public User findByEmail(String email) {
    return User.findByEmail(email).orElse(null);
  }

  public List<User> findAllByRole(String role) {
    return User.findAllByRole(UserRole.valueOf(role));
  }
}
