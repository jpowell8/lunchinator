package com.ics.lunchinator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joshpowell
 */
@Data
@AllArgsConstructor
public class Ballot {

  //Nullable
  public Restaurant suggestion;
  //Nullable
  public Restaurant winner;
  @NonNull
  public List<Restaurant> choices;

}
