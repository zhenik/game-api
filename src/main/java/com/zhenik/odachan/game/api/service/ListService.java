package com.zhenik.odachan.game.api.service;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.user.User;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import com.zhenik.odachan.game.api.repository.ListRepository;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ListService {

  @Inject ListRepository listRepository;

  public ListQuestions save(CreateListCommand createListCommand) {
    return listRepository.save(createListCommand);
  }

  public List<ListQuestions> findAll() {
    return listRepository.listAll();
  }
}
