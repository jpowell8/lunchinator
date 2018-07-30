package com.ics.lunchinator.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * @author joshpowell
 */
@Data
public class Ballot {

  //Nullable
  public Restaurant suggestion;
  //Nullable
  public Restaurant winner;
  @NonNull
  public List<Restaurant> choices;

}
