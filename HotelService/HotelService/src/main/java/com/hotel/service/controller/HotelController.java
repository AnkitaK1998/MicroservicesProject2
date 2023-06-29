package com.hotel.service.controller;

import com.hotel.service.entities.Hotel;
import com.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelServiceImpl;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/")
    ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1=hotelServiceImpl.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
        Hotel hotel1=hotelServiceImpl.getById(hotelId);
        return ResponseEntity.ok(hotel1);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/")
    ResponseEntity<List<Hotel>> getAll(){
        List<Hotel> hotels=hotelServiceImpl.getHotels();
        return ResponseEntity.ok(hotels);
    }

}
