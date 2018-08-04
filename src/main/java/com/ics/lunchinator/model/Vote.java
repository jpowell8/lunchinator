package com.ics.lunchinator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author joshpowell
 */
@Data
@Getter
@AllArgsConstructor
public class Vote {

  private int restaurantId = -1;
  @NonNull
  public String voterName;
  @NonNull
  public String ballotId;
  @NonNull
  public String emailAddress;

  public Vote(String emailAddress, String voterName) {
    this.voterName = voterName;
    this.emailAddress = emailAddress;
  }

  public Vote(Voter voter) {
    this.voterName = voter.getName();
    this.emailAddress = voter.getEmailAddress();
  }

  public boolean matches(Vote vote) {
    if (this == vote) return true;
    return getVoterName().equalsIgnoreCase(vote.getVoterName()) &&
        getEmailAddress().equalsIgnoreCase(vote.getEmailAddress());
  }

}
