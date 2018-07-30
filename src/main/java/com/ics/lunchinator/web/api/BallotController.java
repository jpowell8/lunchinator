package com.ics.lunchinator.web.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ics.lunchinator.web.model.Ballot;
import com.ics.lunchinator.web.model.CreateBallot;
import org.springframework.web.bind.annotation.*;

/**
 * @author joshpowell
 */
@RestController(value = "/api")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BallotController {

  /**
   * GET Ballot
   * TODO include parameter documentation
   * TODO include response type documentation
   */
  @GetMapping(value = "/ballot/{ballotId}")
  public Ballot getBallot(@PathVariable String ballotId) {

    return null;
  }

  /**
   * POST Ballot
   * TODO include parameter documentation
   * TODO include response type documentation
   */
  @PostMapping(value = "/create-ballot")
  public void createBallot(@RequestBody CreateBallot ballotToCreate) {

  }

}
