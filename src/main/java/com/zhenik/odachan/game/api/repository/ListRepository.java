package com.zhenik.odachan.game.api.repository;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.list.ListState;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import com.zhenik.odachan.game.api.dto.commands.UpdateListCommand;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

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

  public void replaceById(ObjectId objectId, UpdateListCommand updateListCommand) {
    ListQuestions oldList = findById(objectId);
    ListQuestions updatedList = ListQuestions.of(updateListCommand);
    System.out.println("UpdatedList -> "+updatedList);
    //oldList.setCreatedAt(updatedList.getCreatedAt());
    oldList.setSegments(updatedList.getSegments());
    oldList.setDeadline(updatedList.getDeadline());
    oldList.setDelivered(updatedList.getDelivered());
    oldList.setState(updatedList.getState());
    oldList.setAssignedDate(updatedList.getAssignedDate());
    oldList.setAssignedToEmail(updatedList.getAssignedToEmail());

    if (updateListCommand.getState().equals(ListState.DELIVERED)) {
      oldList.setAnalytics(updatedList.getAnalytics());
    } else {
      oldList.setAnalytics(oldList.getAnalytics());
    }

    System.out.println("OldList -> "+oldList);
    oldList.update();
  }
}
