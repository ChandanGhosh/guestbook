package com.example.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestsService {
    @Autowired
    private GuestDao guestDao;

    public Iterable<Guest> getAllGuests(){
        return guestDao.findAll();
    }
}
