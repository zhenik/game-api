package com.zhenik.odachan.game.api.service;

import com.zhenik.odachan.game.api.domain.list.Analytics;
import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.list.ListState;
import com.zhenik.odachan.game.api.domain.list.Question;
import com.zhenik.odachan.game.api.domain.list.Segment;
import com.zhenik.odachan.game.api.domain.user.User;
import com.zhenik.odachan.game.api.domain.user.UserRole;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import com.zhenik.odachan.game.api.dto.commands.UpdateListCommand;
import com.zhenik.odachan.game.api.repository.ListRepository;
import com.zhenik.odachan.game.api.repository.UserRepository;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.bson.types.ObjectId;

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

  public ListQuestions findById(String id) {
    try {
      ObjectId objectId = new ObjectId(id);
      return listRepository.findById(objectId);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  public void replaceById(String id, UpdateListCommand updateListCommand) {
    try {
      ObjectId objectId = new ObjectId(id);

      if (updateListCommand.getState().equals(ListState.DELIVERED)) {
        // analytics calculation
        System.out.println("CALCULATION");
        updateListCommand.setAnalytics(calculateAnalytics(updateListCommand));
      } else {
        // nothing
      }

      listRepository.replaceById(objectId, updateListCommand);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }

  // todo: test it, too much logic :(
  private Analytics calculateAnalytics(UpdateListCommand updateListCommand) {
    final Analytics analytics = Analytics.empty();
    final Map<String, Integer> group = Analytics.emptyGroup();

    Integer questionsCount = 0;
    Integer totalScore = 0;

    for (Segment s : updateListCommand.getSegments()) {
      if (s.getQuestions() != null) {
        questionsCount += s.getQuestions().size();
        for (Question q : s.getQuestions()) {
          totalScore += q.getScore();
        }
      }
    }

    if (questionsCount==0) return null;

    Float feedback = (Float.valueOf(totalScore) / questionsCount) * 100;

    for (Segment s : updateListCommand.getSegments()) {
      if (s.getQuestions() != null){
        for (Question q : s.getQuestions()) {
          group.put(q.getAnswer().toString(), group.get(q.getAnswer().toString()) + 1);
        }
      }
    }

    analytics.setQuestions(questionsCount);
    analytics.setScore(totalScore);
    analytics.setFeedback(feedback);
    analytics.setGroup(group);
    return analytics;
  }
}
