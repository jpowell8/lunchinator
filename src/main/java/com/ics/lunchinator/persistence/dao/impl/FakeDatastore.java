package com.ics.lunchinator.persistence.dao.impl;

import com.ics.lunchinator.model.Ballot;
import com.ics.lunchinator.model.Vote;
import com.ics.lunchinator.persistence.dao.LunchEventDao;
import com.ics.lunchinator.persistence.dao.VoteDao;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author joshpowell
 */
@Component
public class FakeDatastore implements LunchEventDao, VoteDao {

  private volatile static Map<String, Ballot> database = new HashMap<>();

  @Override
  public String createBallot(Ballot ballot) {

    String guid = UUID.randomUUID().toString();
    database.put(guid, ballot);
    return guid;
  }

  @Override
  public Ballot getBallot(String ballotId) {

    return database.get(ballotId);
  }


  @Override
  public void addVote(Vote vote) {
    database.get(vote.ballotId).getVotes().stream()
        .filter(v -> v.getEmailAddress().equalsIgnoreCase(vote.getEmailAddress()))
        .findFirst().get()
        .setRestaurantId(vote.getRestaurantId());
  }

}
