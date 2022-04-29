package com.garrsolutions.yelpfusionintegration.controller;

import com.garrsolutions.yelpfusionintegration.model.BusinessReviews;
import com.garrsolutions.yelpfusionintegration.service.YelpFusionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/yelp")
@Slf4j
public class YelpFusionController {

    private final YelpFusionService yelpFusionService;

    public YelpFusionController(YelpFusionService yelpFusionService) {
        this.yelpFusionService = yelpFusionService;
    }

    @GetMapping("reviews")
    public ResponseEntity<BusinessReviews> getStoreLocationReviews(@RequestParam Map<String, String> queryParams) {
        BusinessReviews businessReviews = yelpFusionService.getReviews(queryParams);
        return ResponseEntity.ok(businessReviews);
    }

}
