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
  private RestaurantsAndReviewsClient restaurantsAndReviewsClient;

  @Autowired
  public LunchinatorService(LunchEventDao lunchEventDao, VoteDao voteDao, RestaurantsAndReviewsClient restaurantsAndReviewsClient) {
    this.lunchEventDao = lunchEventDao;
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

    for (Restaurant restaurant : ballotToCreate.getRestaurantList()) {
      List<Review> reviewList = restaurantsAndReviewsClient.getReviewsForRestaurant(restaurant.getName());

      restaurant.setAverageReview(reviewList.stream().map(Review::getRating).max(String::compareTo).get());
      Review topReview = reviewList.stream().sorted(Comparator.comparing(Review::getRating).reversed()).findFirst().get();
      restaurant.setTopReviewer(topReview.getReviewer());
      restaurant.setReview(topReview.getReview());
    }

    return lunchEventDao.createBallot(ballotToCreate);
  }

  /**
   * @param vote
   * @return boolean value representing if the vote was recorded successfully.
   */
  public boolean castVote(Vote vote) {
    Ballot ballot = lunchEventDao.getBallot(vote.ballotId);

    if (isAllowedToVote(vote, ballot) && ballotIsStillOpen(ballot)) {
      lunchEventDao.addVote(vote);
      return true;
    }
    return false;
  }

  public boolean isAllowedToVote(Vote vote, Ballot ballot) {

    return ballot.getVotes().stream()
        .anyMatch(v -> v.getEmailAddress().equalsIgnoreCase(vote.getEmailAddress())
                    && v.getVoterName().equalsIgnoreCase(vote.getVoterName()));
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
    if (ballot == null || ballot.getEndTime() == null) {
      return null;
    }

    Restaurant suggestion = null;
    Restaurant winner = null;
    List<Restaurant> choices = new ArrayList<>();

    //deep copy so that I can mess with the values
    for (Restaurant restaurant : ballot.getRestaurantList()) {
      choices.add(new Restaurant(restaurant));
    }

    Collections.shuffle(choices);

    if (ballotIsStillOpen(ballot)) {
      suggestion = new Restaurant(ballot.getSuggestion());
      suggestion.setVotes(null);
      suggestion.setDescription(null);

      for (Restaurant restaurant : choices) {
        restaurant.setVotes(null);
        restaurant.setTopReviewer(null);
        restaurant.setReview(null);
      }

    } else {
      winner = ballot.getWinner();
      winner.setReview(null);
      winner.setTopReviewer(null);
      winner.setAverageReview(null);
      winner.setDescription(null);
      winner.setDateTime(ballot.getEndTime());

      for (Restaurant restaurant : choices) {
        restaurant.setTopReviewer(null);
        restaurant.setReview(null);
        restaurant.setDescription(null);
        if (restaurant.getVotes() == null) {
          restaurant.setVotes(0);
        }
      }
    }
    //return ballot results
    return new BallotSummary(suggestion, winner, choices);

  }
}
