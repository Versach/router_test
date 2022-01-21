package com.testcompany.router.domain.graph;

import com.testcompany.router.domain.Country;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountriesGraph {
    private final Map<String, Country> countryNodes;

    public Country getCountryNode(String name) {
        return Optional.ofNullable(countryNodes.get(name))
                       .orElseThrow(() -> new NoSuchElementException(String.format("No country node found with name %s", name)));
    }

    public Set<Country> getConnections(Country node) {
        return countryNodes.get(node.getName())
                           .getBorders()
                           .stream()
                           .map(this::getCountryNode)
                           .collect(Collectors.toSet());
    }

    public CountriesGraph(Collection<Country> countries) {
        countryNodes = countries.stream().collect(Collectors.toMap(Country::getName, Function.identity()));
    }
}
