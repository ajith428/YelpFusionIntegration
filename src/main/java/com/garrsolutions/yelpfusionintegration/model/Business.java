package com.garrsolutions.yelpfusionintegration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Business {

    private String id;
    private String alias;
    private String name;
    private String image_url;
    private boolean is_closed;
    private String url;
    private int review_count;
    private final List<Category> categories = new ArrayList<>();
    private double rating;
    private Coordinates coordinates;
    private final List<Object> transactions = new ArrayList<>();
    private Location location;
    private String phone;
    private String display_phone;
    private double distance;

}
