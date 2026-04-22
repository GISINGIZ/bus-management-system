package main;

import model.*;
import util.ReportGenerator;
import factory.BusFactory;
import util.InputValidator;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Set<String> usedIds = new HashSet<>();

    public static void main(String[] args) {
        List<Passenger> passengers = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();

        System.out.println("===== ADVANCED BUS MANAGEMENT SYSTEM =====");

        String busType = readBusType();
        String vehicleId = readUniqueId("Enter Vehicle ID (example B1): ", "B", "Vehicle ID");
        String vehicleName = readNonEmpty("Enter Vehicle Name: ");
        int capacity = readPositiveInt("Enter Capacity: ");
        String fuelType = readNonEmpty("Enter Fuel Type: ");
        String currentLocation = readNonEmpty("Enter Current Location: ");
        String status = readNonEmpty("Enter Bus Status: ");
        String driverName = readNonEmpty("Enter Driver Name: ");
        String busNumber = readNonEmpty("Enter Bus Number: ");
        String routeAssigned = readNonEmpty("Enter Route Assigned: ");

        BusManager bus = BusFactory.createBus(busType, vehicleId, vehicleName, capacity, fuelType,
                currentLocation, status, driverName, busNumber, routeAssigned);

        if (bus == null) {
            System.out.println("Invalid bus type. System stopped.");
            return;
        }

        String passengerId = readUniqueId("Enter Passenger ID (example P1): ", "P", "Passenger ID");
        String passengerName = readNonEmpty("Enter Passenger Name: ");
        String phone = readPhone();
        String email = readEmail();
        Passenger passenger = new Passenger(passengerId, passengerName, phone, email);
        passengers.add(passenger);

        String routeId = readUniqueId("Enter Route ID (example R1): ", "R", "Route ID");
        String startLocation = readNonEmpty("Enter Start Location: ");
        String destination = readNonEmpty("Enter Destination: ");
        double distance = readPositiveDouble("Enter Distance (km): ");
        String estimatedTime = readNonEmpty("Enter Estimated Time: ");
        Route route = new Route(routeId, startLocation, destination, distance, estimatedTime);

        int seatNumber = readSeatNumber(bus.getCapacity());
        if (!bus.bookSeat(seatNumber)) {
            System.out.println("Booking failed due to seat issue.");
            return;
        }

        String ticketId = readUniqueId("Enter Ticket ID (example T1): ", "T", "Ticket ID");
        Ticket ticket = new Ticket(ticketId, passenger, bus, seatNumber, 0);
        double price = ticket.calculateTicketPrice(distance);

        String bookingId = readUniqueId("Enter Booking ID (example BK1): ", "BK", "Booking ID");
        String bookingDate = readNonEmpty("Enter Booking Date: ");
        Booking booking = new Booking(bookingId, passenger, ticket, bookingDate, "Pending");
        booking.confirmBooking();
        bookings.add(booking);

        String paymentId = readUniqueId("Enter Payment ID (example PAY1): ", "PAY", "Payment ID");
        String paymentMethod = readNonEmpty("Enter Payment Method: ");
        Payment payment = new Payment(paymentId, price, paymentMethod, "Pending");
        payment.processPayment();
        payments.add(payment);

        bus.startVehicle();
        bus.assignRoute(route.getStartLocation() + " to " + route.getDestination());
        bus.updateLocation(destination);

        Schedule schedule = new Schedule("S1", bus, "08:00", "10:00", route);
        Maintenance maintenance = new Maintenance("M1", bus, "2026-04-20", "General engine check");

        System.out.println("\n===== SYSTEM OUTPUT =====");
        System.out.println("\nBus Details:");
        System.out.println(bus);
        System.out.println("\nPassenger Details:");
        System.out.println(passenger);
        System.out.println("\nRoute Details:");
        System.out.println(route);
        System.out.println("\nTicket Summary:");
        System.out.println(ticket);
        System.out.println("\nBooking Summary:");
        System.out.println(booking);
        System.out.println("\nPayment Status:");
        System.out.println(payment);
        System.out.println("\nTracking Info:");
        System.out.println(bus.trackLocation());
        System.out.println("\nSchedule Details:");
        System.out.println(schedule);
        System.out.println("\nMaintenance Details:");
        maintenance.scheduleMaintenance();
        System.out.println(maintenance);

        ReportGenerator.generateDailyBookingsReport(bookings);
        ReportGenerator.generateRevenueReport(payments);
        ReportGenerator.generateBusUsageReport(bus);
        ReportGenerator.generatePassengerStatistics(passengers);
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (!InputValidator.isEmpty(value)) {
                return value;
            }
            System.out.println("Error: input cannot be empty. Please try again.");
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (InputValidator.isPositiveInt(value)) {
                return Integer.parseInt(value);
            }
            System.out.println("Error: enter a valid positive whole number.");
        }
    }

    private static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (InputValidator.isPositiveDouble(value)) {
                return Double.parseDouble(value);
            }
            System.out.println("Error: enter a valid positive number.");
        }
    }

    private static String readPhone() {
        while (true) {
            System.out.print("Enter Phone Number (10 digits): ");
            String phone = scanner.nextLine();
            if (InputValidator.isValidPhone(phone)) {
                return phone;
            }
            System.out.println("Error: phone number must have exactly 10 digits.");
        }
    }

    private static String readEmail() {
        while (true) {
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            if (InputValidator.isValidEmail(email)) {
                return email;
            }
            System.out.println("Error: invalid email format.");
        }
    }

    private static String readUniqueId(String prompt, String prefix, String fieldName) {
        while (true) {
            System.out.print(prompt);
            String id = scanner.nextLine();
            if (InputValidator.isEmpty(id)) {
                System.out.println("Error: " + fieldName + " cannot be empty.");
            } else if (!InputValidator.isValidId(id, prefix)) {
                System.out.println("Error: " + fieldName + " must start with " + prefix + " followed by numbers.");
            } else if (usedIds.contains(id)) {
                System.out.println("Error: duplicate ID not allowed.");
            } else {
                usedIds.add(id);
                return id;
            }
        }
    }

    private static String readBusType() {
        while (true) {
            System.out.print("Enter Bus Type (City, Express, Luxury, School, Tourist, Electric): ");
            String type = scanner.nextLine();
            if (type.equalsIgnoreCase("City") || type.equalsIgnoreCase("Express") ||
                type.equalsIgnoreCase("Luxury") || type.equalsIgnoreCase("School") ||
                type.equalsIgnoreCase("Tourist") || type.equalsIgnoreCase("Electric")) {
                return type;
            }
            System.out.println("Error: invalid bus type. Choose City, Express, Luxury, School, Tourist, or Electric.");
        }
    }

    private static int readSeatNumber(int capacity) {
        while (true) {
            int seat = readPositiveInt("Enter Seat Number: ");
            if (seat <= capacity) {
                return seat;
            }
            System.out.println("Error: seat number cannot exceed bus capacity of " + capacity);
        }
    }
}
