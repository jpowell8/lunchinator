package com.ics.lunchinator.webservice;

import com.ics.lunchinator.model.Ballot;
import com.ics.lunchinator.model.BallotSummary;
import com.ics.lunchinator.service.LunchinatorService;
import com.ics.lunchinator.webservice.httpMappedExceptions.InvalidTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.net.URI;

import static com.ics.lunchinator.service.LunchinatorService.isTimeValid;

/**
 * @author joshpowell
 */
@RestController
@RequestMapping(value = "/api")
public class BallotController {

  private LunchinatorService lunchinatorService;

  @Autowired
  public BallotController(@NotNull LunchinatorService lunchinatorService) {
    this.lunchinatorService = lunchinatorService;
  }

  /**
   * GET BallotSummary
   * TODO include parameter documentation
   * TODO include response type documentation
   */
  @GetMapping(value = "/ballot/{ballotId}")
  public ResponseEntity<BallotSummary> getBallot(@PathVariable String ballotId) {

    BallotSummary summary = lunchinatorService.getBallotSummary(ballotId);
    if (summary == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(summary);
  }

  /**
   * POST BallotSummary
   * TODO include parameter documentation
   * TODO include response type documentation
   */
  @PostMapping(value = "/create-ballot")
  public ResponseEntity<String> createBallot(@RequestBody Ballot ballotToCreate) {

    if (!isListOfVotersValid(ballotToCreate)) {
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.WARNING, "Invalid list of voters.");
      return ResponseEntity.badRequest().headers(headers).build();
    }

    if (!isTimeValid(ballotToCreate)) {
      throw new InvalidTimeException("Time or date is invalid, must be later than the current time");
    }

    String uri = lunchinatorService.createBallot(ballotToCreate);

    return ResponseEntity.created(URI.create(uri)).body("{ \"ballotId\" : \"" + uri + "\" }");
  }

  private boolean isListOfVotersValid(Ballot ballot) {
    if (ballot.getVotes().isEmpty()) {
      return false;
    }
    //Insert any other sanitation checks here, or in the setter for the Ballot voter object
    return true;
  }

}
