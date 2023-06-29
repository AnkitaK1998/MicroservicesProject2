package com.ms.user.service.services.impl;

import com.ms.user.service.entities.Hotel;
import com.ms.user.service.entities.Rating;
import com.ms.user.service.entities.User;
import com.ms.user.service.exception.ResourceNotFoundException;
import com.ms.user.service.external.services.HotelService;
import com.ms.user.service.external.services.RatingService;
import com.ms.user.service.repository.UserRepository;
import com.ms.user.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RatingService ratingService;
    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        //generate unique userid
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll() ;
    }

    @Override
    public User getUser(String userId) {
      User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server!! :"+userId));
//        http://localhost:8084/rating/users/{userId}
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class );
        List<Rating> ratings= Arrays.stream(ratingsOfUser).toList();

        //using feign client
//        List<Rating> ratings=ratingService.getRatingByUserId(user.getUserId());

        List<Rating> ratingList=ratings.stream().map(rating -> {
        //api call to hotel service to get hotel
//        ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
//        rating.setHotel(hotel.getBody());
//        log.info("{} {}",hotel.getBody(),hotel.getStatusCode());

            //using feign client
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
    }
    ).collect(Collectors.toList());



     user.setRatings(ratingList);
     log.info("{}",ratingList);
      return user;

    }
}
