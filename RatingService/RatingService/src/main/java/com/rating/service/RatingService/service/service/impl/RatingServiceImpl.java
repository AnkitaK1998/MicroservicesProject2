package com.rating.service.RatingService.service.service.impl;

import com.rating.service.RatingService.entities.Rating;
import com.rating.service.RatingService.repository.RatingRepository;
import com.rating.service.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
   private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        String randomUserId= UUID.randomUUID().toString();
        rating.setRatingId(randomUserId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
