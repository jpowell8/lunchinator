package com.ics.lunchinator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author joshpowell
 */
@Data
public class Ballot {

  @NonNull
  private Date endTime = getDefaultTime();
  @NonNull
  private List<Vote> votes;

  @JsonIgnore
  private List<Restaurant> restaurantList;

  public Ballot(Date endTime, List<Voter> voters) {
    this.endTime = endTime;
    this.votes = voters.stream().map(v -> new Vote(v)).collect(Collectors.toList());
  }

  /**
   * Accepts String input for endTime, does necessary parsing for deserialization
   * @param value
   */
  public void setEndTime(String value) {
    if (isEmpty(value)) {
//      endTime = getDefaultTime();
      return;
    }

    SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yy hh:mm");
    try {
      endTime = formatter1.parse(value);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  private Date getDefaultTime() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY,11);
    cal.set(Calendar.MINUTE,45);

    Date timeToRun = cal.getTime();

    if(System.currentTimeMillis() > timeToRun.getTime()){
      cal.add(Calendar.DATE,1);
    }
    return cal.getTime();
  }

  public void setVoters(List<Voter> voters) {

    Map<String, String> groupedByEmail = new HashMap<>();

    voters.stream()
        .filter(v -> !isEmpty(v.getName()) && !isEmpty(v.getEmailAddress()))
        .forEach(voter -> groupedByEmail.put(voter.getEmailAddress(), voter.getName()));

    this.votes = groupedByEmail.entrySet().stream()
        .map(entry -> new Vote(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }

  public Restaurant getSuggestion() {

    return restaurantList.stream().sorted(Comparator.comparing(Restaurant::getAverageReview).reversed()).findFirst().get();
  }

  public Restaurant getWinner() {

    List<Integer> allVotes = votes.stream().filter(vote -> vote.getRestaurantId() > -1)
        .map(Vote::getRestaurantId).collect(Collectors.toList());

    for(Restaurant restaurant: restaurantList) {
      restaurant.setVotes((int) allVotes.stream().filter(v -> v.intValue() == restaurant.getId()).count());
    }

    Restaurant mostVotedRestaurant = restaurantList.stream()
        .sorted(Comparator.comparing(Restaurant::getVotes).reversed())
        .findFirst().get();

    if (mostVotedRestaurant == null) {
      Restaurant defaultWinner = getSuggestion();
    }

    return mostVotedRestaurant;
    }
}
