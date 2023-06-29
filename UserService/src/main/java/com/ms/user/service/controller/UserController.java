package com.ms.user.service.controller;

import com.ms.user.service.entities.User;
import com.ms.user.service.services.UserService;
import com.ms.user.service.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService UserServiceImpl;

    @PostMapping("/")
    ResponseEntity<User> createUser(@RequestBody User user){
        User user1=UserServiceImpl.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount=1;
    @GetMapping("/{userId}")
  @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback" )
   // @Retry(name="ratingHotelService", fallbackMethod ="ratingHotelFallback")
    //@RateLimiter(name="userRateLimiter", fallbackMethod ="ratingHotelFallback" )
    ResponseEntity<User> getUserById(@PathVariable String userId){
       log.info("Retry count:{}",retryCount);
       retryCount++;
        User user1=UserServiceImpl.getUser(userId);
        return ResponseEntity.ok(user1);
    }
//creating fall back method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//       log.info("Fallback is executed because service is down:", ex.getMessage());
       ex.printStackTrace();
        User user= User.builder()
               .email("dummy@gmail.com")
               .name("dummy")
               .about("This is dummy user data")
               .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/")
    ResponseEntity<List<User>> getUsers(){
        List<User> users=UserServiceImpl.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
