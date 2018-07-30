package com.ics.lunchinator.model;

import lombok.Data;
import lombok.NonNull;

/**
 * @author joshpowell
 */
@Data
public class Vote {

  @NonNull
  public int restaurantId;
  @NonNull
  public String voterName;
  @NonNull
  public String ballotId;
  @NonNull
  public String emailAddress;

}
