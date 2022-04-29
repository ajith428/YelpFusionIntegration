package com.garrsolutions.yelpfusionintegration.service;

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

    public YelpModel getStoreLocationReviews(Map<String, String> queryParams) {
        String yelpURL = yelpFusionApiUrl + "businesses/search";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(yelpURL);
        if (Objects.nonNull(queryParams)) {
            queryParams.forEach(uriBuilder::queryParam);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setBearerAuth(yelpFusionApiKey);
            HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<YelpModel> yelpModelResponseEntity
                    = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, httpEntity, YelpModel.class, queryParams);
            return yelpModelResponseEntity.getBody();
        } else {
            log.info("location queryParam is mandatory");
        }

        return null;
    }

}
