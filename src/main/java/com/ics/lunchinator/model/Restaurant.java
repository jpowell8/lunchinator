package com.ics.lunchinator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL;

/**
 * @author joshpowell
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
@Setter
@JsonSerialize(include = NON_NULL)
public class Restaurant {

  private int id;
  private String name;
  private String description;

  private String averageReview;
  @JsonProperty("TopReviewer")
  private String topReviewer;
  @JsonProperty("Review")
  private String review;
  private Integer votes;
  @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "MMMMMMMMM dd, yyyy hh:mma")
  private Date dateTime;

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
