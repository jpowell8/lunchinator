package com.ics.lunchinator.web.model;

import com.ics.lunchinator.persistence.model.Restaurant;
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
