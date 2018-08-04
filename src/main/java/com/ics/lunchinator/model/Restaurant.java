package com.ics.lunchinator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author joshpowell
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
@Setter
public class Restaurant {

  private int id;
  private String name;
  private String description;

  //added
  private String averageReview;
  @JsonProperty("TopReviewer")
  private String topReviewer;
  @JsonProperty("Review")
  private String review;
  private int votes;

  public Restaurant(Restaurant restaurant) {
    this.id = restaurant.getId();
    this.name = restaurant.getName();
    this.description = restaurant.getDescription();
    this.averageReview = restaurant.getAverageReview();
    this.topReviewer = restaurant.getTopReviewer();
    this.review = restaurant.getReview();
    this.votes = restaurant.getVotes();
  }

  @Override
  public String
  toString() {
    return "Restaurant{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", averageReview='" + averageReview + '\'' +
        ", topReviewer='" + topReviewer + '\'' +
        ", review='" + review + '\'' +
        ", votes=" + votes +
        '}';
  }
}
