package com.zhenik.odachan.game.api.service;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import com.zhenik.odachan.game.api.repository.ListRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ListService {

  @Inject ListRepository listRepository;

  public ListQuestions save(CreateListCommand createListCommand) {
    return listRepository.save(createListCommand);
  }
}
