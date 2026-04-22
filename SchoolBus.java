package model;

public class SchoolBus extends BusManager {
    private String schoolName;

    public SchoolBus() {
        super();
    }

    public SchoolBus(String vehicleId, String vehicleName, int capacity, String fuelType,
                     String currentLocation, String status, String driverName, String busNumber,
                     String routeAssigned, String schoolName) {
        super(vehicleId, vehicleName, capacity, fuelType, currentLocation, status, driverName, busNumber, routeAssigned);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public void startVehicle() {
        updateStatus("School Pickup Running");
        System.out.println("SchoolBus has started for " + schoolName);
    }

    @Override
    public boolean checkAvailability() {
        return super.checkAvailability() && getStatus().equalsIgnoreCase("School Pickup Running");
    }

    @Override
    public String generateVehicleReport() {
        return "SchoolBus Report -> School: " + schoolName + ", " + super.generateVehicleReport();
    }

    @Override
    public String toString() {
        return "SchoolBus{" + super.toString() + ", schoolName='" + schoolName + '\'' + '}';
    }
}
