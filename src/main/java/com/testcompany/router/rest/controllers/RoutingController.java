package com.testcompany.router.rest.controllers;

import com.testcompany.router.domain.Country;
import com.testcompany.router.rest.dto.RoutingResultDto;
import com.testcompany.router.rest.exceptions.NoRouteException;
import com.testcompany.router.services.RoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoutingController {
    private final RoutingService routingService;

    @GetMapping("/routing/{origin}/{destination}")
    RoutingResultDto getRoute(@PathVariable String origin, @PathVariable String destination) {
        var routeCountryList = routingService.findRoute(origin, destination)
                                             .orElseThrow(() -> new NoRouteException(origin, destination));
        var route = routeCountryList.stream()
                                    .map(Country::getName)
                                    .toList();
        return new RoutingResultDto(route);
    }

}
