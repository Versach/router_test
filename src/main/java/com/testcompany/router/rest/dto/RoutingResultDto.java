package com.testcompany.router.rest.dto;

import lombok.Value;

import java.util.List;

@Value
public class RoutingResultDto {
    private final List<String> route;
}
