package model;

public class TouristBus extends BusManager {
    private String guideName;

    public TouristBus() {
        super();
    }

    public TouristBus(String vehicleId, String vehicleName, int capacity, String fuelType,
                      String currentLocation, String status, String driverName, String busNumber,
                      String routeAssigned, String guideName) {
        super(vehicleId, vehicleName, capacity, fuelType, currentLocation, status, driverName, busNumber, routeAssigned);
        this.guideName = guideName;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    @Override
    public void startVehicle() {
        updateStatus("Tour Running");
        System.out.println("TouristBus has started with guide " + guideName);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 100;
    }

    @Override
    public String trackLocation() {
        return "Tourist bus is currently at tourist point near " + getCurrentLocation();
    }

    @Override
    public String toString() {
        return "TouristBus{" + super.toString() + ", guideName='" + guideName + '\'' + '}';
    }
}
