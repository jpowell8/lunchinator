package com.ics.lunchinator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author joshpowell
 */
@Data
@AllArgsConstructor
public class Voter {

  String name;
  String emailAddress;

}
