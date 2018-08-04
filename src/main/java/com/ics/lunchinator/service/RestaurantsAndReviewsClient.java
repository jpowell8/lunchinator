package com.ics.lunchinator.service;

import com.ics.lunchinator.model.Restaurant;
import com.ics.lunchinator.model.Review;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author joshpowell
 */
@Component
public class RestaurantsAndReviewsClient {

  private static final String RESTAURANTS_URL = "https://interview-project-17987.herokuapp.com/api/restaurants";
  private static final String REVIEWS_URL = "https://interview-project-17987.herokuapp.com/api/reviews/";

  RestTemplate restTemplate = new RestTemplate();

  public List<Restaurant> getAllRestaurants(){
    HttpHeaders headers=new HttpHeaders();
    headers.set("Content-Type", "application/json");
    HttpEntity requestEntity=new HttpEntity(headers);

    return restTemplate.exchange(RESTAURANTS_URL,
            HttpMethod.GET, null, new ParameterizedTypeReference<List<Restaurant>>() {}).getBody();


  }

  public List<Review> getReviewsForRestaurant(String restaurantName) {
      return restTemplate.exchange(REVIEWS_URL + restaurantName,
          HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {}).getBody();
    }

}
