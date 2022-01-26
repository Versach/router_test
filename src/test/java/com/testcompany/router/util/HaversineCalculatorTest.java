package com.testcompany.router.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class HaversineCalculatorTest {
    @Test
    void shouldComputeDistance() {
        assertThat(HaversineCalculator.computeDistance(10, 20, 30, 10)).isCloseTo(2454.593, offset(0.001d));
    }
}
