package model;

public class ElectricBus extends BusManager {
    private double batteryCapacity;

    public ElectricBus() {
        super();
    }

    public ElectricBus(String vehicleId, String vehicleName, int capacity, String fuelType,
                       String currentLocation, String status, String driverName, String busNumber,
                       String routeAssigned, double batteryCapacity) {
        super(vehicleId, vehicleName, capacity, fuelType, currentLocation, status, driverName, busNumber, routeAssigned);
        this.batteryCapacity = batteryCapacity;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void startVehicle() {
        updateStatus("Electric Bus Active");
        System.out.println("ElectricBus started silently.");
    }

    @Override
    public double calculateFuelConsumption(double distance) {
        return distance * 0.2;
    }

    @Override
    public void performMaintenanceCheck() {
        System.out.println("Battery and charging system checked. Battery: " + batteryCapacity + " kWh");
    }

    @Override
    public String toString() {
        return "ElectricBus{" + super.toString() + ", batteryCapacity=" + batteryCapacity + '}';
    }
}
