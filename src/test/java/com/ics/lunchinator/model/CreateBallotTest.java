package com.ics.lunchinator.model;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Date;

/**
 * @author joshpowell
 */
public class CreateBallotTest {

  @Test
  public void setEndTimeDefault() {
    Ballot ballot = new Ballot(new Date(), Lists.emptyList());
    ballot.setEndTime(null);
    Date defaultEndTime = ballot.getEndTime();
  }
}
