package com.testcompany.router.rest.exceptions;

public class NoRouteException extends RuntimeException {
    public NoRouteException(String origin, String destination) {
        super(String.format("Could not find route from origin %s to destination %s", origin, destination));
    }

}
