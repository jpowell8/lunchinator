package com.ics.lunchinator.service;

import com.ics.lunchinator.model.CreateBallot;

import java.time.Instant;
import java.util.Date;

/**
 * @author joshpowell
 */
public class LunchinatorService {

  public static boolean isTimeValid(CreateBallot createBallot) {

    if (createBallot.getEndTime().before(Date.from(Instant.now()))) {
      return false;
    }
    return true;
  }
}
