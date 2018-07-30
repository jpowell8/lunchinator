package com.ics.lunchinator.service;

import com.ics.lunchinator.model.Restaurant;
import com.ics.lunchinator.model.Review;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author joshpowell
 */
public class RestaurantsAndReviewsClient {

  RestTemplate restTemplate = new RestTemplate();

  public List<Restaurant> getAllRestaurants(){
    ResponseEntity<List<Restaurant>> responseEntity =
        restTemplate.exchange("https://interview-project-17987.herokuapp.com/api/restaurants",
            HttpMethod.GET, null, new ParameterizedTypeReference<List<Restaurant>>() {
            });
    return responseEntity.getBody();
  };

  //TODO pull URL to const
  public Restaurant getRestaurant(String id) {
    return restTemplate.getForObject("https://interview-project-17987.herokuapp.com/api/restaurants/" + id, Restaurant.class);
  }

  public List<Review> getAllReviews(){
    return restTemplate.exchange("https://interview-project-17987.herokuapp.com/api/reviews",
        HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {}).getBody();
  };

  public List<Review> getReviewsForRestaurant(String restaurantName) {
      return restTemplate.exchange("https://interview-project-17987.herokuapp.com/api/reviews/" + restaurantName,
          HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {}).getBody();
    };

}
