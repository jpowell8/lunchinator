package com.ics.lunchinator.persistence.dao;

import com.ics.lunchinator.persistence.model.LunchEvent;
import com.ics.lunchinator.web.model.Ballot;

/**
 * @author joshpowell
 */
public interface LunchEventDao {

  //create lunch event
  String createBallot();

  //read next lunch event
  Ballot readBallot(String ballotId);
}
