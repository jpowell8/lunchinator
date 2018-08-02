package com.ics.lunchinator.webservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ics.lunchinator.model.Ballot;
import com.ics.lunchinator.model.CreateBallot;
import com.ics.lunchinator.model.Restaurant;
import com.ics.lunchinator.webservice.httpMappedExceptions.InvalidTimeException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.ics.lunchinator.service.LunchinatorService.isTimeValid;

/**
 * @author joshpowell
 */
@RestController
@RequestMapping(value = "/api")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BallotController {

  /**
   * GET Ballot
   * TODO include parameter documentation
   * TODO include response type documentation
   */
  @GetMapping(value = "/ballot/{ballotId}")
  public Ballot getBallot(@PathVariable String ballotId) {

    return new Ballot(new Restaurant(), new Restaurant(), new ArrayList<Restaurant>());
  }

  /**
   * POST Ballot
   * TODO include parameter documentation
   * TODO include response type documentation
   */
  @PostMapping(value = "/create-ballot")
  public void createBallot(@RequestBody CreateBallot ballotToCreate) {

//    if ballotToCreate.getEndTime()

//    //TODO check time is valid
    if(!isTimeValid(ballotToCreate)) {
      throw new InvalidTimeException("Time or date is invalid, must be later than the current time");
    }

  }

}
