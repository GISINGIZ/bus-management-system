package util;

import main.Booking;
import model.BusManager;
import model.Passenger;
import model.Payment;
import java.util.List;

public class ReportGenerator {
    public static void generateDailyBookingsReport(List<Booking> bookings) {
        System.out.println("\n--- Daily Bookings Report ---");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public static void generateRevenueReport(List<Payment> payments) {
        double total = 0;
        for (Payment payment : payments) {
            if (payment.getPaymentStatus().equalsIgnoreCase("Paid")) {
                total += payment.getAmount();
            }
        }
        System.out.println("\n--- Revenue Report ---");
        System.out.println("Total Revenue: " + total + " RWF");
    }

    public static void generateBusUsageReport(BusManager bus) {
        System.out.println("\n--- Bus Usage Report ---");
        System.out.println(bus.generateVehicleReport());
    }

    public static void generatePassengerStatistics(List<Passenger> passengers) {
        System.out.println("\n--- Passenger Statistics ---");
        System.out.println("Total Passengers: " + passengers.size());
    }
}
