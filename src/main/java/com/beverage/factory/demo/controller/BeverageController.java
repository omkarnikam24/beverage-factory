package com.beverage.factory.demo.controller;

import com.beverage.factory.demo.entity.ResponseDetails;
import com.beverage.factory.demo.service.BeverageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeverageController {

    private static final Logger log = LoggerFactory.getLogger(BeverageController.class);

    @Autowired
    private BeverageService service;

    @PostMapping(value = "/order")
    public ResponseEntity<?> calculateTotal(@RequestBody List<String> orders) {
        log.info("orders : {}", orders);
        if(orders.isEmpty()) {
            log.info("Invalid Order, input array cannot be empty");
            return new ResponseEntity<>("Invalid Order, input array cannot be empty", HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(service.processOrder(orders), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Order Processing failed, {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
