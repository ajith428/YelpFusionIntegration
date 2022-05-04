package com.garrsolutions.yelpfusionintegration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    private String id;
    private String url;
    private String text;
    private int rating;
    private String time_created;
    private User user;

}
