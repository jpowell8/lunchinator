package com.ics.lunchinator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * @author joshpowell
 */
@Data
@AllArgsConstructor
public class BallotSummary {

  //Nullable
  public Restaurant suggestion;
  //Nullable
  public Restaurant winner;
  @NonNull
  public List<Restaurant> choices;

}
