package com.ics.lunchinator.web.model;

import lombok.Data;
import lombok.NonNull;

import java.time.Instant;
import java.util.List;

/**
 * @author joshpowell
 */
@Data
public class CreateBallot {

  @NonNull
  public Instant endTime;
  @NonNull
  public List<String> voters;

}
