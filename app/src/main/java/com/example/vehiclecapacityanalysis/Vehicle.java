package com.example.vehiclecapacityanalysis;

public class Vehicle {
    private String name;
    private double maxPayload;
    private double range;
    private String mode; // "Street" or "UAM"

    // New attributes for specifications
    private String dimensions;
    private double emptyWeight;
    private double takeoffWeight;
    private double distance;

    // Constructor for basic vehicle information
    public Vehicle(String name, double maxPayload, double range, String mode) {
        this.name = name;
        this.maxPayload = maxPayload;
        this.range = range;
        this.mode = mode;
    }

    // Constructor for detailed vehicle information
    public Vehicle(String name, double maxPayload, double range, String mode, String dimensions, double emptyWeight, double takeoffWeight, double distance) {
        this.name = name;
        this.maxPayload = maxPayload;
        this.range = range;
        this.mode = mode;
        this.dimensions = dimensions;
        this.emptyWeight = emptyWeight;
        this.takeoffWeight = takeoffWeight;
        this.distance = distance;
    }

    // Getters for basic attributes
    public String getName() {
        return name;
    }

    public double getMaxPayload() {
        return maxPayload;
    }

    public double getRange() {
        return range;
    }

    public String getMode() {
        return mode;
    }

    // Getters for new attributes
    public String getDimensions() {
        return dimensions;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public double getTakeoffWeight() {
        return takeoffWeight;
    }

    public double getDistance() {
        return distance;
    }
}
