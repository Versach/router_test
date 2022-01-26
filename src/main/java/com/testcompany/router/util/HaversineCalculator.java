package com.testcompany.router.util;

public final class HaversineCalculator {
    private static final double RADIUS = 6372.8; // Earth's Radius, in kilometers

    public static double computeDistance(double lat1, double lng1, double lat2, double lng2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2), 2)
                   + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return RADIUS * c;
    }

    private HaversineCalculator() {
    }

}
