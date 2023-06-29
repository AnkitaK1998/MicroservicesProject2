package com.rating.service.RatingService.controller;

import com.rating.service.RatingService.entities.Rating;
import com.rating.service.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/")
    public ResponseEntity<Rating> create(@RequestBody Rating rating){

        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getAllRating(){
        return ResponseEntity.ok(ratingService.getAllRating());
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userID}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userID ){
        return ResponseEntity.ok(ratingService.getAllByUserId(userID));
    }
    @GetMapping("/hotel/{hotelID}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelID){
        return ResponseEntity.ok(ratingService.getAllByHotelId(hotelID));
    }


}
