package com.testcompany.router.services;

import com.testcompany.router.domain.Country;
import com.testcompany.router.domain.graph.CountriesGraph;
import com.testcompany.router.domain.graph.RouteNode;
import com.testcompany.router.util.HaversineCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoutingService {
    private final CountriesGraph countriesGraph;

    public Optional<List<Country>> findRoute(String origin, String destination) {
        var originCountry = countriesGraph.getCountryNode(origin);
        var destinationCountry = countriesGraph.getCountryNode(destination);
        return findRoute(originCountry, destinationCountry);
    }

    public Optional<List<Country>> findRoute(Country from, Country to) {
        Queue<RouteNode> openSet = new PriorityQueue<>();
        Map<Country, RouteNode> allNodes = new HashMap<>();

        RouteNode start = new RouteNode(from, null, 0d, computeDistance(from, to));
        openSet.add(start);
        allNodes.put(from, start);

        while (!openSet.isEmpty()) {
            RouteNode next = openSet.poll();
            if (next.getCurrent().equals(to)) {
                List<Country> route = new ArrayList<>();
                RouteNode current = next;
                do {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                } while (current != null);
                return Optional.of(route);
            }

            countriesGraph.getConnections(next.getCurrent()).forEach(connection -> {
                RouteNode nextNode = allNodes.getOrDefault(connection, new RouteNode(connection));
                allNodes.put(connection, nextNode);

                double newScore = next.getRouteScore() + computeDistance(next.getCurrent(), connection);
                if (newScore < nextNode.getRouteScore()) {
                    nextNode.setPrevious(next.getCurrent());
                    nextNode.setRouteScore(newScore);
                    nextNode.setEstimatedScore(newScore + computeDistance(connection, to));
                    openSet.add(nextNode);
                }
            });
        }
        return Optional.empty();
    }

    private double computeDistance(Country from, Country to) {
        return HaversineCalculator.computeDistance(from.getLat(), from.getLng(), to.getLat(), to.getLng());
    }

}
