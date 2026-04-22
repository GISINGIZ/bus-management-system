package factory;


import model.*;

public class BusFactory {
    public static BusManager createBus(String type, String vehicleId, String vehicleName, int capacity,
                                       String fuelType, String currentLocation, String status,
                                       String driverName, String busNumber, String routeAssigned) {
        switch (type.toLowerCase()) {
            case "city":
                return new CityBus(vehicleId, vehicleName, capacity, fuelType, currentLocation, status,
                        driverName, busNumber, routeAssigned, true);
            case "express":
                return new ExpressBus(vehicleId, vehicleName, capacity, fuelType, currentLocation, status,
                        driverName, busNumber, routeAssigned, true);
            case "luxury":
                return new LuxuryBus(vehicleId, vehicleName, capacity, fuelType, currentLocation, status,
                        driverName, busNumber, routeAssigned, true);
            case "school":
                return new SchoolBus(vehicleId, vehicleName, capacity, fuelType, currentLocation, status,
                        driverName, busNumber, routeAssigned, "Green Hills Academy");
            case "tourist":
                return new TouristBus(vehicleId, vehicleName, capacity, fuelType, currentLocation, status,
                        driverName, busNumber, routeAssigned, "John Guide");
            case "electric":
                return new ElectricBus(vehicleId, vehicleName, capacity, fuelType, currentLocation, status,
                        driverName, busNumber, routeAssigned, 300.0);
            default:
                return null;
        }
    }
}
