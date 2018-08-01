package com.ics.lunchinator.webservice;

import com.ics.lunchinator.model.CreateBallot;
import com.ics.lunchinator.model.Vote;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joshpowell
 */
@RestController(value = "/api/vote")
public class VoteController {

  //POST vote
  @PostMapping
  public void castVote(@RequestBody Vote vote) {

    //check that id corresponds to a real ballot

    //
  }

}
