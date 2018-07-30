package com.ics.lunchinator.web.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joshpowell
 */
@RestController(value = "/api/vote")
public class VoteController {

  //POST vote
  @PostMapping
  public void castVote() {

  }

}
