package com.ics.lunchinator.persistence.dao;


import com.ics.lunchinator.model.Vote;

import java.util.List;

/**
 * @author joshpowell
 */
public interface VoteDao {

  //get votes
  List<Vote> getVotes(String ballotId);

  //Record Vote
  boolean addVote(Vote vote);

}
