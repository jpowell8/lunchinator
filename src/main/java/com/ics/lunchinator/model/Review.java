package com.ics.lunchinator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author joshpowell
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {

  private String reviewer;
  private String rating;
  private String review;

}
