package com.testcompany.router.services;

import com.testcompany.router.domain.graph.CountriesGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;
import static com.testcompany.testUtils.TestEntryCreator.createCountry;

@ExtendWith(SpringExtension.class)
class RoutingServiceTest {
    @MockBean
    private CountriesGraph countriesGraph;

    private RoutingService routingService;

    @BeforeEach
    void init() {
        routingService = new RoutingService(countriesGraph);
    }

    @Test
    void routingServiceShouldCallCountriesGraph() {
        //given
        var cze = createCountry("CZE");
        var ita = createCountry("ITA");
        when(countriesGraph.getCountryNode("CZE")).thenReturn(cze);
        when(countriesGraph.getCountryNode("ITA")).thenReturn(ita);

        //when
        routingService.findRoute("CZE", "ITA");

        //then
        verify(countriesGraph, times(1)).getCountryNode("CZE");
        verify(countriesGraph, times(1)).getCountryNode("ITA");
        verify(countriesGraph, times(1)).getConnections(cze);
    }
}
