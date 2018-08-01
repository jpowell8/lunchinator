package com.ics.lunchinator.persistence.dao.impl;

import com.ics.lunchinator.model.Vote;
import com.ics.lunchinator.persistence.dao.LunchEventDao;
import com.ics.lunchinator.persistence.dao.VoteDao;
import com.ics.lunchinator.model.Ballot;

import java.util.List;

/**
 * @author joshpowell
 */
public class FakeDatastore implements LunchEventDao, VoteDao {


  @Override
  public String createBallot() {
    return null;
  }

  @Override
  public Ballot readBallot(String ballotId) {
    return null;
  }

  @Override
  public List<Vote> getVotes(String ballotId) {
    return null;
  }

  @Override
  public boolean addVote(Vote vote) {
    return false;
  }

}
