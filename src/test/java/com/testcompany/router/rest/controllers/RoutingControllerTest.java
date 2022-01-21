package com.testcompany.router.rest.controllers;

import com.testcompany.router.services.RoutingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.testcompany.testUtils.TestEntryCreator.createCountry;


@WebMvcTest(RoutingController.class)
class RoutingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoutingService service;

    @Test
    void routingShouldReturnRouteResult() throws Exception {
        var serviceResult = Optional.of(
                List.of(createCountry("CZE"),
                        createCountry("AUT"),
                        createCountry("ITA")));
        when(service.findRoute("CZE", "ITA")).thenReturn(serviceResult);
        this.mockMvc.perform(get("/routing/CZE/ITA")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().json("{\"route\": [\"CZE\", \"AUT\", \"ITA\"]}"));
    }

    @Test
    void routingShouldReturnBadRequest_whenRouteNotFound() throws Exception {
        when(service.findRoute("CZE", "BOL")).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/routing/CZE/BOL")).andDo(print()).andExpect(status().isBadRequest())
                    .andExpect(content().string("Could not find route from origin CZE to destination BOL"));
    }

}
