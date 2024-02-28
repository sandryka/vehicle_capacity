package com.example.vehiclecapacityanalysis;

import java.util.ArrayList;
import java.util.List;

public class VehicleCalculator {
    private double weight;
    private String selectedMode;
    private List<Vehicle> vehicleList;

    public VehicleCalculator() {
        vehicleList = new ArrayList<>();
        populateVehicleData();
    }

    // Populate the list of vehicles with sample data
    private void populateVehicleData() {
        // UAM Aircraft
        vehicleList.add(new Vehicle("Airbus CityAirbus", 551, 60, "UAM"));
        vehicleList.add(new Vehicle("Boeing PAV", 500, 50, "UAM"));
        vehicleList.add(new Vehicle("Bell Nexus Air Taxi", 6000, 60, "UAM"));
        vehicleList.add(new Vehicle("WISK Cora", 400, 62, "UAM"));
        vehicleList.add(new Vehicle("EHang 216", 485, 22, "UAM"));
        vehicleList.add(new Vehicle("Joby Aviation S4", 840, 150, "UAM"));
        vehicleList.add(new Vehicle("Vertical Aerospace VX4", 992, 100, "UAM"));
        vehicleList.add(new Vehicle("Lilium Jet", 441, 190, "UAM"));

        // Traditional Delivery Vehicles
        vehicleList.add(new Vehicle("Semi-Trailers", 40000, 0, "Street"));
        vehicleList.add(new Vehicle("Flatbeds", 80000, 0, "Street"));
        vehicleList.add(new Vehicle("Step Decks", 52000, 0, "Street"));
        vehicleList.add(new Vehicle("Dry Vans", 45000, 0, "Street"));
        vehicleList.add(new Vehicle("Reefers", 45000, 0, "Street"));
        vehicleList.add(new Vehicle("Box Trucks", 26000, 0, "Street"));
        vehicleList.add(new Vehicle("Tankers", 11000, 0, "Street"));
        vehicleList.add(new Vehicle("C Class", 3000, 0, "Street"));
        // ... Add more vehicles as needed ...
    }

    // Set the selected mode (either "Street" or "UAM")
    public void setSelectedMode(String mode) {
        this.selectedMode = mode;
    }

    // Set the weight for which vehicles need to be computed
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Compute the best vehicles based on selected mode and weight

    public List<Vehicle> computeBestVehicles() {
        List<Vehicle> suitableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getMode().equalsIgnoreCase(selectedMode) && vehicle.getMaxPayload() >= weight) {
                suitableVehicles.add(vehicle);
            }
        }
        return suitableVehicles; // Return the list of suitable vehicles
    }
}

