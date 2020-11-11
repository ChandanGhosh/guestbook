package com.example.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@BasePathAwareController
public class GuestsController {

    // @Autowired
    // private RestartApp restartApp;

    @Autowired
    private GuestsService guestService;

    @RequestMapping(value = "/guests", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Guest>> getAll() {
        return ResponseEntity.ok(guestService.getAllGuests());
    }

    // @RequestMapping(value = "/restart", method = RequestMethod.GET)
    // public String restart() {
    //     restartApp.restartApp();
    //     return "restarted";
    // }
}
