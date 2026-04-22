package model;

public class LuxuryBus extends BusManager {
    private boolean wifiAvailable;

    public LuxuryBus() {
        super();
    }

    public LuxuryBus(String vehicleId, String vehicleName, int capacity, String fuelType,
                     String currentLocation, String status, String driverName, String busNumber,
                     String routeAssigned, boolean wifiAvailable) {
        super(vehicleId, vehicleName, capacity, fuelType, currentLocation, status, driverName, busNumber, routeAssigned);
        this.wifiAvailable = wifiAvailable;
    }

    public boolean isWifiAvailable() {
        return wifiAvailable;
    }

    public void setWifiAvailable(boolean wifiAvailable) {
        this.wifiAvailable = wifiAvailable;
    }

    @Override
    public void startVehicle() {
        updateStatus("Luxury Trip Started");
        System.out.println("LuxuryBus has started with premium services.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 120;
    }

    @Override
    public String generateVehicleReport() {
        return "LuxuryBus -> " + super.generateVehicleReport() + ", WiFi: " + wifiAvailable;
    }

    @Override
    public String toString() {
        return "LuxuryBus{" + super.toString() + ", wifiAvailable=" + wifiAvailable + '}';
    }
}
