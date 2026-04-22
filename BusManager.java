package model;

import interfaces.Bookable;
import interfaces.Trackable;
import java.util.HashSet;
import java.util.Set;

public class BusManager extends Vehicle implements Bookable, Trackable {
    private String driverName;
    private String busNumber;
    private String routeAssigned;
    private Set<Integer> bookedSeats;

    public BusManager() {
        super();
        this.driverName = "";
        this.busNumber = "";
        this.routeAssigned = "";
        this.bookedSeats = new HashSet<>();
    }

    public BusManager(String vehicleId, String vehicleName, int capacity, String fuelType,
                      String currentLocation, String status, String driverName,
                      String busNumber, String routeAssigned) {
        super(vehicleId, vehicleName, capacity, fuelType, currentLocation, status);
        this.driverName = driverName;
        this.busNumber = busNumber;
        this.routeAssigned = routeAssigned;
        this.bookedSeats = new HashSet<>();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getRouteAssigned() {
        return routeAssigned;
    }

    public void setRouteAssigned(String routeAssigned) {
        this.routeAssigned = routeAssigned;
    }

    public Set<Integer> getBookedSeats() {
        return bookedSeats;
    }

    @Override
    public void startVehicle() {
        setStatus("Running");
        System.out.println(getVehicleName() + " has started.");
    }

    @Override
    public void stopVehicle() {
        setStatus("Stopped");
        System.out.println(getVehicleName() + " has stopped.");
    }

    @Override
    public double calculateFuelConsumption(double distance) {
        return distance / 5.0;
    }

    @Override
    public boolean checkAvailability() {
        return bookedSeats.size() < getCapacity() && !getStatus().equalsIgnoreCase("Maintenance");
    }

    @Override
    public void assignRoute(String route) {
        this.routeAssigned = route;
    }

    @Override
    public void updateLocation(String newLocation) {
        setCurrentLocation(newLocation);
    }

    @Override
    public void performMaintenanceCheck() {
        System.out.println("Maintenance check completed for bus " + busNumber);
    }

    @Override
    public String generateVehicleReport() {
        return "Bus Report: " + getVehicleName() + ", Driver: " + driverName +
                ", Route: " + routeAssigned + ", Booked Seats: " + bookedSeats.size();
    }

    @Override
    public boolean bookSeat(int seatNumber) {
        if (seatNumber <= 0 || seatNumber > getCapacity()) {
            System.out.println("Invalid seat number.");
            return false;
        }
        if (bookedSeats.contains(seatNumber)) {
            System.out.println("Seat already booked.");
            return false;
        }
        if (bookedSeats.size() >= getCapacity()) {
            System.out.println("Bus is fully booked.");
            return false;
        }
        bookedSeats.add(seatNumber);
        return true;
    }

    @Override
    public boolean cancelBooking(int seatNumber) {
        return bookedSeats.remove(seatNumber);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 50;
    }

    @Override
    public String trackLocation() {
        return "Current location of " + getVehicleName() + ": " + getCurrentLocation();
    }

    @Override
    public void updateStatus(String status) {
        setStatus(status);
    }

    @Override
    public String toString() {
        return "BusManager{" +
                "vehicle=" + super.toString() +
                ", driverName='" + driverName + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", routeAssigned='" + routeAssigned + '\'' +
                ", bookedSeats=" + bookedSeats +
                '}';
    }
}
