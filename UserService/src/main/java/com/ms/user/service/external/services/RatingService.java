package com.ms.user.service.external.services;

import com.ms.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @GetMapping("/users/{userID}")
    List<Rating> getRatingByUserId(@PathVariable("userID") String userID );
}
