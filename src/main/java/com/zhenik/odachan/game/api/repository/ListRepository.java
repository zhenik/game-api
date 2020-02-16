package com.zhenik.odachan.game.api.repository;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListRepository implements PanacheMongoRepository<ListQuestions> {

  private static final Sort recent = Sort.by("createdAt").and("updateAt");

  public ListQuestions save(CreateListCommand createListCommand) {
    ListQuestions list = ListQuestions.of(createListCommand);
    list.persist();
    return list;
  }

  public List<ListQuestions> findAllLists() {
    return ListQuestions.findAll().list();
  }

  public List<ListQuestions> findListsAssignedToEmail(String email) {
    return ListQuestions.find("assignedToEmail", email).list();
  }
}
