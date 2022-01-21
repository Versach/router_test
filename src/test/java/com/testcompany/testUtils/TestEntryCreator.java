package com.testcompany.testUtils;

import com.testcompany.router.domain.Country;

import java.util.Set;

public class TestEntryCreator {
    public static Country createCountry(String name) {
        return new Country(name, new double[]{1.0, 1.0}, Set.of());
    }
}
