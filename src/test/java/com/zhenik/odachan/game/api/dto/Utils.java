package com.zhenik.odachan.game.api.dto;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.list.Question;
import com.zhenik.odachan.game.api.domain.list.Segment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {


  public static List<ListQuestions> getCollectionOfListQuestions() {
    final ArrayList<ListQuestions> listQuestions = new ArrayList<>();
    // 2 lists
    listQuestions.add(getSomeList());
    listQuestions.add(getSomeList());
    return listQuestions;
  }

  // list with 2 segments, each segment has 2 questions
  public static ListQuestions getSomeList() {
    final ListQuestions listQuestions = new ListQuestions();
    final Segment segment1 = new Segment();
    final Segment segment2 = new Segment();

    final Question q1 = new Question();
    q1.setScore(0);
    final Question q2 = new Question();
    q2.setScore(1);

    segment1.setQuestions(Arrays.asList(q2, q2));
    segment2.setQuestions(Arrays.asList(q1, q1));

    listQuestions.setSegments(Arrays.asList(segment1, segment2));
    return listQuestions;
  }

  // 3 lists, with 70% score
  public static List<ListQuestions> getSeventyPercentScore() {
    final ArrayList<ListQuestions> listQuestions = new ArrayList<>();
    // 2 lists
    listQuestions.add(getList_TwoSegments_SevenQuestions_AllCorrect());
    listQuestions.add(getList_OneSegment_ThreeQuestions_AllIncorrect());
    return listQuestions;
  }

  public static ListQuestions getList_TwoSegments_SevenQuestions_AllCorrect() {
    final ListQuestions listQuestions = new ListQuestions();
    final Segment segment1 = new Segment();
    final Segment segment2 = new Segment();

    final Question q1 = new Question();
    q1.setScore(1);

    segment1.setQuestions(Arrays.asList(q1, q1, q1, q1, q1)); // 5
    segment2.setQuestions(Arrays.asList(q1, q1)); // 2

    listQuestions.setSegments(Arrays.asList(segment1, segment2));
    return listQuestions;
  }
  public static ListQuestions getList_OneSegment_ThreeQuestions_AllIncorrect() {
    final ListQuestions listQuestions = new ListQuestions();
    final Segment segment1 = new Segment();
    final Segment segment2 = new Segment();

    final Question q1 = new Question();
    q1.setScore(0);

    segment1.setQuestions(Arrays.asList(q1, q1, q1)); // 3

    listQuestions.setSegments(Arrays.asList(segment1, segment2)); // empty segment
    System.out.println("Segment segment2 "+segment2 );
    return listQuestions;
  }
}
