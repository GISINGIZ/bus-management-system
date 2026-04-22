package model;

public class CityBus extends BusManager {
    private boolean standingAllowed;

    public CityBus() {
        super();
    }

    public CityBus(String vehicleId, String vehicleName, int capacity, String fuelType,
                   String currentLocation, String status, String driverName, String busNumber,
                   String routeAssigned, boolean standingAllowed) {
        super(vehicleId, vehicleName, capacity, fuelType, currentLocation, status, driverName, busNumber, routeAssigned);
        this.standingAllowed = standingAllowed;
    }

    public boolean isStandingAllowed() {
        return standingAllowed;
    }

    public void setStandingAllowed(boolean standingAllowed) {
        this.standingAllowed = standingAllowed;
    }

    @Override
    public void startVehicle() {
        updateStatus("City Route Running");
        System.out.println("CityBus is now operating in urban areas.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 30;
    }

    @Override
    public String generateVehicleReport() {
        return "CityBus Report -> " + super.generateVehicleReport() + ", Standing Allowed: " + standingAllowed;
    }

    @Override
    public String toString() {
        return "CityBus{" + super.toString() + ", standingAllowed=" + standingAllowed + '}';
    }
}
