package com.rating.service.RatingService.service;

import com.rating.service.RatingService.entities.Rating;
import org.apache.catalina.User;

import java.util.List;

public interface RatingService {

   Rating create(Rating rating);
   List<Rating> getAllRating();

    List<Rating> getAllByUserId(String userId);
    List<Rating> getAllByHotelId(String hotelId);
}
