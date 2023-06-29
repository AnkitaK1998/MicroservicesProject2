package com.hotel.service.service;

import com.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    Hotel getById(String id);

    List<Hotel> getHotels();


}
