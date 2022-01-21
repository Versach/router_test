package com.testcompany.router.domain.graph;

import com.testcompany.router.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteNode implements Comparable<RouteNode> {
    private final Country current;
    private Country previous;
    private double routeScore;
    private double estimatedScore;

    public RouteNode(Country current) {
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    @Override
    public int compareTo(RouteNode other) {
        if (this.estimatedScore > other.estimatedScore) {
            return 1;
        } else if (this.estimatedScore < other.estimatedScore) {
            return -1;
        } else {
            return 0;
        }
    }
}
