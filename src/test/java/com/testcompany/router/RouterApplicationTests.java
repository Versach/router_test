package com.testcompany.router;

import com.testcompany.router.domain.graph.CountriesGraph;
import com.testcompany.router.rest.controllers.RoutingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RouterApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoutingController routingController;
    @Autowired
    private CountriesGraph countriesGraph;

    @Test
    void contextLoads() {
        assertThat(routingController).isNotNull();
        assertThat(countriesGraph).isNotNull();
    }

    @Test
    void routingShouldReturnCorrectRoute() throws Exception {
        this.mockMvc.perform(get("/routing/CZE/ITA")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().json("{'route': ['CZE', 'AUT', 'ITA']}"));
    }

    @Test
    void routingShouldReturnBadRequest_whenRouteNotFound() throws Exception {
        this.mockMvc.perform(get("/routing/CZE/BOL")).andDo(print()).andExpect(status().isBadRequest())
                    .andExpect(content().string("Could not find route from origin CZE to destination BOL"));
    }

}
