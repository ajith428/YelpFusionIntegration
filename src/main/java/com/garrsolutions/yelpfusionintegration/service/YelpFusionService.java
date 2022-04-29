package com.garrsolutions.yelpfusionintegration.service;

import com.garrsolutions.yelpfusionintegration.model.BusinessReview;
import com.garrsolutions.yelpfusionintegration.model.BusinessReviews;
import com.garrsolutions.yelpfusionintegration.model.Reviews;
import com.garrsolutions.yelpfusionintegration.model.YelpModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class YelpFusionService {

    @Value("${yelp.fusion.api.url}")
    private String yelpFusionApiUrl;

    @Value("${yelp.fusion.api.key}")
    private String yelpFusionApiKey;

    private final RestTemplate restTemplate;

    public YelpFusionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public YelpModel getStoreLocations(Map<String, String> queryParams) {
        String yelpURL = yelpFusionApiUrl + "businesses/search";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(yelpURL);
        if (Objects.nonNull(queryParams)) {
            queryParams.forEach(uriBuilder::queryParam);

            HttpEntity<String> httpEntity = getYelpHttpEntity();

            ResponseEntity<YelpModel> yelpModelResponseEntity
                    = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, httpEntity, YelpModel.class, queryParams);
            return yelpModelResponseEntity.getBody();
        } else {
            log.info("location queryParam is mandatory");
        }

        return null;
    }

    public BusinessReviews getReviews(Map<String, String> queryParams) {

        BusinessReviews businessReviews = new BusinessReviews();
        String yelpURL = yelpFusionApiUrl + "businesses/{id}/reviews";

        YelpModel yelpModel = getStoreLocations(queryParams);

        HttpEntity<String> httpEntity = getYelpHttpEntity();

        Optional.of(yelpModel).ifPresent(ylpmodel -> yelpModel.getBusinesses().forEach(business -> {
            ResponseEntity<Reviews> yelpReviewsResponseEntity
                    = restTemplate.exchange(yelpURL, HttpMethod.GET, httpEntity, Reviews.class, business.getAlias());
            Reviews reviews = yelpReviewsResponseEntity.getBody();

            BusinessReview businessReview = BusinessReview.builder()
                    .business(business)
                    .reviews(reviews)
                    .build();

            businessReviews.getBusinessReviewsList().add(businessReview);
        }));

        return businessReviews;
    }

    private HttpEntity<String> getYelpHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(yelpFusionApiKey);
        return new HttpEntity<>(httpHeaders);
    }

}
