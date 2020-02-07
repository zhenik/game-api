package com.zhenik.odachan.game.api.service;

import com.zhenik.odachan.game.api.domain.User;
import com.zhenik.odachan.game.api.dto.commands.UserSaveCommand;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

    public User save(UserSaveCommand userSaveCommand) {
        User user = User.of(userSaveCommand);
        user.persist();
        return user;
    }

    public List<User> findAll() {
        return User.listAll();
    }
}
