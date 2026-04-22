package model;

public class ExpressBus extends BusManager {
    private boolean directTrip;

    public ExpressBus() {
        super();
    }

    public ExpressBus(String vehicleId, String vehicleName, int capacity, String fuelType,
                      String currentLocation, String status, String driverName, String busNumber,
                      String routeAssigned, boolean directTrip) {
        super(vehicleId, vehicleName, capacity, fuelType, currentLocation, status, driverName, busNumber, routeAssigned);
        this.directTrip = directTrip;
    }

    public boolean isDirectTrip() {
        return directTrip;
    }

    public void setDirectTrip(boolean directTrip) {
        this.directTrip = directTrip;
    }

    @Override
    public void startVehicle() {
        updateStatus("Express Service Running");
        System.out.println("ExpressBus started for long-distance service.");
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 70;
    }

    @Override
    public double calculateFuelConsumption(double distance) {
        return distance / 6.5;
    }

    @Override
    public String toString() {
        return "ExpressBus{" + super.toString() + ", directTrip=" + directTrip + '}';
    }
}
