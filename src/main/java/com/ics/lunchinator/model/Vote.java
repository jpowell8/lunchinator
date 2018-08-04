package com.ics.lunchinator.model;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

/**
 * @author joshpowell
 */
@Data
@Getter
public class Vote {

  private int restaurantId = -1;
  @NonNull
  public String voterName;
  @NonNull
  public String ballotId;
  @NonNull
  public String emailAddress;

  public Vote(String voterName, String emailAddress) {
    this.voterName = voterName;
    this.emailAddress = emailAddress;
  }

  public Vote(Voter voter) {
    this.voterName = voter.getName();
    this.emailAddress = voter.getEmailAddress();
  }

  public boolean matches(Vote vote) {
    if (this == vote) return true;
    return Objects.equals(getVoterName(), vote.getVoterName()) &&
        Objects.equals(getEmailAddress(), vote.getEmailAddress());
  }

}
