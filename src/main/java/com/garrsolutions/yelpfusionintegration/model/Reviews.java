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
public class Reviews {

    private final List<Review> reviews = new ArrayList<>();
    private int total;
    private final List<String> possible_languages = new ArrayList<>();
}
