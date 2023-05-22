package com.sevyh.sevyhuserservice.userservice.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevyh.sevyhuserservice.userservice.service.KeycloakService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/users")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private KeycloakService keycloakService;

    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/uuids")
    public ResponseEntity<List<String>> getUserUuids() {
        try {
            List<String> uuids = keycloakService.getUserUuids();
            return new ResponseEntity<>(uuids, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while getting user uuids", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
