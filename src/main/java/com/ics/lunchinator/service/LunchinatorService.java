package com.ics.lunchinator.service;

import com.ics.lunchinator.model.*;
import com.ics.lunchinator.persistence.dao.LunchEventDao;
import com.ics.lunchinator.persistence.dao.VoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;

/**
 * @author joshpowell
 */
@Component
public class LunchinatorService {

  private static final int NUMBER_OF_RESTAURANT_CHOICES = 5;
  private LunchEventDao lunchEventDao;
  private VoteDao voteDao;
  private RestaurantsAndReviewsClient restaurantsAndReviewsClient;

  @Autowired
  public LunchinatorService(LunchEventDao lunchEventDao, VoteDao voteDao, RestaurantsAndReviewsClient restaurantsAndReviewsClient) {
    this.lunchEventDao = lunchEventDao;
    this.voteDao = voteDao;
    this.restaurantsAndReviewsClient = restaurantsAndReviewsClient;
  }

  public static boolean isTimeValid(Ballot createBallot) {

    if (createBallot.getEndTime().before(Date.from(Instant.now()))) {
      return false;
    }
    return true;
  }

  @NotNull
  public String createBallot(Ballot ballotToCreate) {
    //get 5 restaurants and store them with the ballot.
    ballotToCreate.setRestaurantList(getRestaurantChoices());

    System.out.println(ballotToCreate.getRestaurantList().get(0));

    for (Restaurant restaurant : ballotToCreate.getRestaurantList()) {
      List<Review> reviewList = restaurantsAndReviewsClient.getReviewsForRestaurant(restaurant.getName());

      System.out.println(reviewList.size());
      System.out.println(reviewList.get(0));

      restaurant.setAverageReview(reviewList.stream().map(Review::getRating).max(String::compareTo).get());
      Review topReview = reviewList.stream().sorted(Comparator.comparing(Review::getRating).reversed()).findFirst().get();
      restaurant.setTopReviewer(topReview.getReviewer());
      restaurant.setReview(topReview.getReview());
    }

    return lunchEventDao.createBallot(ballotToCreate);
  }

  /**
   *
   * @param vote
   * @return boolean value representing if the vote was recorded successfully.
   */
  public boolean castVote(Vote vote) {
    Ballot ballot = lunchEventDao.getBallot(vote.ballotId);

    if(isAllowedToVote(vote, ballot) && ballotIsStillOpen(ballot)) {
      lunchEventDao.addVote(vote);
      return true;
    }
    return false;
  }

  public boolean isAllowedToVote(Vote vote, Ballot ballot) {
    return ballot.getVotes().stream().anyMatch(v -> v.matches(vote));
  }

  public boolean ballotIsStillOpen(Ballot ballot) {
    return ballot.getEndTime().after(new Date());
  }

  private List<Restaurant> getRestaurantChoices() {
    List<Restaurant> choices = restaurantsAndReviewsClient.getAllRestaurants();
    Collections.shuffle(choices);
    return choices.subList(0, NUMBER_OF_RESTAURANT_CHOICES - 1);
  }

  public Ballot getBallot(String id) {
   return lunchEventDao.getBallot(id);
  }

  public BallotSummary getBallotSummary(String id) {
    Ballot ballot = this.lunchEventDao.getBallot(id);
    if(ballot == null || ballot.getEndTime() == null) {
      return null;
    }

    Restaurant suggestion = null;
    Restaurant winner = null;
    List<Restaurant> choices = new ArrayList<>();

    //deep copy so that I can mess with the values
    for(Restaurant restaurant : ballot.getRestaurantList()) {
      choices.add(new Restaurant(restaurant));
    }

    Collections.shuffle(choices);

    if(ballotIsStillOpen(ballot)) {
      suggestion = ballot.getSuggestion();
    } else {
      winner = ballot.getWinner();
    }
    //return ballot results
    return new BallotSummary(suggestion, winner, choices);

  }
}
