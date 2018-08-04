package com.ics.lunchinator.webservice;

import com.ics.lunchinator.model.Ballot;
import com.ics.lunchinator.model.Vote;
import com.ics.lunchinator.service.LunchinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joshpowell
 */
@RestController(value = "/api/vote")
public class VoteController {

  private LunchinatorService lunchinatorService;

  @Autowired
  public VoteController(LunchinatorService lunchinatorService) {
    this.lunchinatorService = lunchinatorService;
  }

  //POST vote
  @PostMapping
  public ResponseEntity<Void> castVote(@RequestBody Vote vote) {

    //check that id corresponds to a real ballot
    Ballot ballot = lunchinatorService.getBallot(vote.ballotId);

    // 404 if id doesn't correspond to a real ballot
    if(ballot == null) {
      return ResponseEntity.notFound().build();
    }

    // 409 if deadline has passed (per requirements) return 409, also if the voter is not on the list of apprived voters
    if(!lunchinatorService.isAllowedToVote(vote, ballot) || !lunchinatorService.ballotIsStillOpen(ballot)) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    lunchinatorService.castVote(vote);

    return ResponseEntity.ok().build();
  }

}
