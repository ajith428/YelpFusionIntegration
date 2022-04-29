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
public class Location {

    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String zip_code;
    private String country;
    private String state;
    private final List<String> display_address = new ArrayList<>();

}
