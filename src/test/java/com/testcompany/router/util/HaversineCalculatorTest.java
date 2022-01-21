package com.testcompany.router.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class HaversineCalculatorTest {
    @Test
    void shouldComputeDistance() {
        assertThat(HaversineCalculator.computeDistance(10, 20, 30, 20)).isCloseTo(2224.526, offset(0.001d));
    }
}
