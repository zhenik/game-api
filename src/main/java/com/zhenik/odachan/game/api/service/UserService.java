package com.zhenik.odachan.game.api.service;

import com.zhenik.odachan.game.api.domain.user.User;
import com.zhenik.odachan.game.api.dto.commands.UserSaveCommand;
import com.zhenik.odachan.game.api.repository.UserRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class UserService {
  private static final Logger LOGGER = Logger.getLogger(UserService.class);

  @Inject UserRepository userRepository;

  public User save(UserSaveCommand userSaveCommand) {
    return userRepository.save(userSaveCommand);
  }

  public List<User> findAll() {
    return userRepository.listAll();
  }

  public User findByEmail(String email) {
      return User.find("email", email).singleResult();
  }

  public Long deleteByEmail(String email) {
    return User.delete("email", email);
  }
}
