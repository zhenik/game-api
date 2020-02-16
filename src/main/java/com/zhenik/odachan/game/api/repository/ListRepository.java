package com.zhenik.odachan.game.api.repository;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListRepository implements PanacheMongoRepository<ListQuestions> {

  public ListQuestions save(CreateListCommand createListCommand) {
    ListQuestions list = ListQuestions.of(createListCommand);
    list.persist();
    return list;
  }
}