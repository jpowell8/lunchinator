package com.ics.lunchinator.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL;

/**
 * @author joshpowell
 */
@Data
@AllArgsConstructor
@JsonSerialize(include = NON_NULL)
public class BallotSummary {

  //Nullable
  public Restaurant suggestion;
  //Nullable
  public Restaurant winner;
  @NonNull
  public List<Restaurant> choices;

}
