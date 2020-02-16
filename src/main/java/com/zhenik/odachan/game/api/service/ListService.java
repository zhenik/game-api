package com.zhenik.odachan.game.api.service;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.user.User;
import com.zhenik.odachan.game.api.domain.user.UserRole;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import com.zhenik.odachan.game.api.repository.ListRepository;
import com.zhenik.odachan.game.api.repository.UserRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ListService {

  @Inject ListRepository listRepository;
  @Inject UserRepository userRepository;

  public ListQuestions save(CreateListCommand createListCommand) {
    return listRepository.save(createListCommand);
  }

  public List<ListQuestions> findAll() {
    return listRepository.listAll();
  }

  public List<ListQuestions> findByEmail(String email) {
    User byEmail = userRepository.findByEmail(email);
    if (byEmail != null) {
      if (byEmail.getRole() == UserRole.ADMIN) {
        // all lists
        return listRepository.findAllLists();
      } else {
        // only assigned to that user
        return listRepository.findListsAssignedToEmail(email);
      }
    }
    return null;
  }
}
