package com.testcompany.router.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.Set;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    @JsonIgnore
    private String name;
    private double lat;
    private double lng;
    private Set<String> borders;

    @JsonCreator
    public Country(
            @JsonProperty("cca3") String cca3,
            @JsonProperty("latlng") double[] latlng,
            @JsonProperty("borders") Set<String> borders) {
        this.name = cca3;
        this.lat = latlng[0];
        this.lng = latlng[1];
        this.borders = borders;
    }

}
