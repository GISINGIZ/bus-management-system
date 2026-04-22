package interfaces;

public interface Bookable {
    boolean bookSeat(int seatNumber);
    boolean cancelBooking(int seatNumber);
    double calculateFare(double distance);
}
