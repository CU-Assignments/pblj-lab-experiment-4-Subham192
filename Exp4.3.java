import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private final int totalSeats;
    private final boolean[] seats;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats];
    }

    public void bookSeat(int seatNumber, String userType) {
        lock.lock();
        try {
            if (seatNumber < 1 || seatNumber > totalSeats) {
                System.out.println("Invalid seat number!");
                return;
            }
            if (seats[seatNumber - 1]) {
                System.out.println(userType + ": Seat " + seatNumber + " is already booked!");
            } else {
                seats[seatNumber - 1] = true;
                System.out.println(userType + " booked seat " + seatNumber);
            }
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem bookingSystem;
    private final int seatNumber;
    private final String userType;

    public BookingThread(TicketBookingSystem bookingSystem, int seatNumber, String userType) {
        this.bookingSystem = bookingSystem;
        this.seatNumber = seatNumber;
        this.userType = userType;
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(seatNumber, userType);
    }
}

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(5);

        BookingThread user1 = new BookingThread(bookingSystem, 1, "Anish (VIP)");
        BookingThread user2 = new BookingThread(bookingSystem, 2, "Bobby (Regular)");
        BookingThread user3 = new BookingThread(bookingSystem, 3, "Charlie (VIP)");
        BookingThread user4 = new BookingThread(bookingSystem, 4, "Bobby (Regular)");
        BookingThread user5 = new BookingThread(bookingSystem, 1, "Bobby (Regular)");
        BookingThread user6 = new BookingThread(bookingSystem, 3, "User (Regular)");
        BookingThread user7 = new BookingThread(bookingSystem, 0, "User (Regular)");
        BookingThread user8 = new BookingThread(bookingSystem, 6, "User (Regular)");

        user1.setPriority(Thread.MAX_PRIORITY);
        user2.setPriority(Thread.NORM_PRIORITY);
        user3.setPriority(Thread.MAX_PRIORITY);
        user4.setPriority(Thread.MIN_PRIORITY);
        user5.setPriority(Thread.NORM_PRIORITY);
        user6.setPriority(Thread.NORM_PRIORITY);
        user7.setPriority(Thread.NORM_PRIORITY);
        user8.setPriority(Thread.NORM_PRIORITY);

        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();
        user6.start();
        user7.start();
        user8.start();
    }
}



