package com.ics.lunchinator.persistence.dao;

import com.ics.lunchinator.model.Ballot;
import com.ics.lunchinator.model.Vote;

/**
 * @author joshpowell
 */
public interface LunchEventDao {

  void addVote(Vote vote);
  //create lunch event
  String createBallot(Ballot ballot);

  //read next lunch event
  Ballot getBallot(String ballotId);
}
