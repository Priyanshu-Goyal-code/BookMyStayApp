import java.util.*;

public class BookMyStayApp {
    // Map to store Room ID and its availability (Uniqueness enforcement)
    private Map<Integer, String> rooms;
    // Queue to handle booking requests in order (FIFO principle)
    private Queue<String> bookingRequests;

    public BookMyStayApp() {
        rooms = new HashMap<>();
        bookingRequests = new LinkedList<>();

        rooms.put(101, "Available");
        rooms.put(102, "Available");
        rooms.put(103, "Available");
    }
    public void addBookingRequest(String customerName) {
        bookingRequests.add(customerName);
        System.out.println("Request added for: " + customerName);
    }

    // Method to process the next request in the queue (FIFO)
    public void processNextBooking(int roomId) {
        if (bookingRequests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        if (rooms.containsKey(roomId) && rooms.get(roomId).equals("Available")) {
            String customer = bookingRequests.poll(); // Remove from queue
            rooms.put(roomId, "Booked by " + customer); // Update status
            System.out.println("Room " + roomId + " successfully booked for " + customer);
        } else {
            System.out.println("Room " + roomId + " is unavailable or does not exist.");
        }
    }

    public void showRoomStatus() {
        System.out.println("\n--- Current Room Status ---");
        rooms.forEach((id, status) -> System.out.println("Room " + id + ": " + status));
    }

    public static void main(String[] args) {
        BookMyStayApp app = new BookMyStayApp();

        // Simulate fair request handling
        app.addBookingRequest("Alice");
        app.addBookingRequest("Bob");

        // Process bookings
        app.processNextBooking(101);
        app.processNextBooking(102);

        app.showRoomStatus();
    }
}
