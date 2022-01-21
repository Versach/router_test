package com.testcompany.router;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcompany.router.domain.Country;
import com.testcompany.router.domain.graph.CountriesGraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class RouterApplication {
    @Value("${countries.path.remote}")
    private String pathToRemoteCountriesResource;

    @Value("${countries.path.local}")
    private String pathToLocalCountriesResource;

    @Bean
    public CountriesGraph countriesGraph(ResourceLoader resourceLoader) throws IOException {
        Resource countriesJsonResource = resourceLoader.getResource(pathToRemoteCountriesResource);
        if (!countriesJsonResource.exists()) {
            countriesJsonResource = resourceLoader.getResource(pathToLocalCountriesResource);
        }
        var mapper = new ObjectMapper();
        var countriesList = mapper.readValue(countriesJsonResource.getInputStream(), new TypeReference<List<Country>>() {});
        return new CountriesGraph(countriesList);
    }

    public static void main(String[] args) {
        SpringApplication.run(RouterApplication.class, args);
    }

}
