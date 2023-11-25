# Berjaya-Hotel-Management-System

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.Font;

public class LinkedListMain {
    private static final String BOOKING_FILE_PATH = "D:\\SEM3\\PROJECT\\Booking.txt";

    public static void main(String[] args) {
        java.util.LinkedList<HotelBooking> hotelBookings = new java.util.LinkedList<>();

        // Read data from "Booking.txt" file and populate the linked list
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(BOOKING_FILE_PATH));
            String line;
            while ((line = inFile.readLine()) != null) {
                String[] bookingData = line.split(";");
                HotelBooking newBooking = new HotelBooking(
                        bookingData[0], bookingData[1], Integer.parseInt(bookingData[2]),
                        Integer.parseInt(bookingData[3]), bookingData[4], Integer.parseInt(bookingData[5]),
                        Integer.parseInt(bookingData[6]), bookingData[7].charAt(0),
                        Boolean.parseBoolean(bookingData[8]), bookingData[9], bookingData[10].charAt(0),
                        bookingData[11]
                );
                hotelBookings.add(newBooking);
            }
            inFile.close();
          } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading data from file: " + e.getMessage());
          }

        int choice;
		boolean exitProgram = false;
		
		do {
		    String[] options = 
		    {
		        "Exit",
		        "Add Booking",
		        "Remove Booking",
		        "Display Unpaid Bookings",
		        "Display Paid Bookings",
		        "Display Upcoming Bookings",
		        "Update Payment Status",
		        "Calculate Total Payments"
		    };
		
		    choice = JOptionPane.showOptionDialog(
		        null,
		        " --- Hotel Management System ---\n 1. Add Booking\n 2. Remove Booking\n 3. Display Unpaid Bookings\n"
		            + " 4. Display Paid Bookings\n 5. Display Upcoming Bookings\n 6. Update Payment Status\n 7. Calculate Total Payments\n 0. Exit",
		        "Hotel Management System",
		        JOptionPane.DEFAULT_OPTION,
		        JOptionPane.PLAIN_MESSAGE,
		        null,
		        options,
		        options[0]
		    );
		
		    // User clicked the 'Cancel' button or closed the dialog
		    if (choice == JOptionPane.CLOSED_OPTION) {
		        exitProgram = true;
		    } else if (choice == 1) {
		        addBooking(hotelBookings);
		        updateBookingFile(hotelBookings);
		    } else if (choice == 2) {
		        removeBooking(hotelBookings);
		        updateBookingFile(hotelBookings);
		    } else if (choice == 3) {
		        displayUnpaidBookings(hotelBookings);
		    } else if (choice == 4) {
		        displayPaidBookings(hotelBookings);
		    } else if (choice == 5) {
		        displayUpcomingBookings(hotelBookings);
		    } else if (choice == 6) {
		        updatePaymentStatus(hotelBookings);
		        updateBookingFile(hotelBookings);
		    } else if (choice == 7) {
		        calculateTotalPayments(hotelBookings);
		    } else if (choice == 0) {
		        exitProgram = true;
		        JOptionPane.showMessageDialog(null, "Exiting Hotel Management System...");
		    } else {
		        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
		    }
	    } while (!exitProgram);
    }
	
	//Method for add booking
    public static void addBooking(java.util.LinkedList<HotelBooking> hotelBookings) {
        JOptionPane.showMessageDialog(null, "---- Add Booking ----");

        String guestName = JOptionPane.showInputDialog(null, "Guest Name:");
        String phoneNumber = JOptionPane.showInputDialog(null, "Phone Number:");
        int numOfGuests = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of Guests:"));
        int bookingID = Integer.parseInt(JOptionPane.showInputDialog(null, "Booking ID:"));
        String bookingDate = JOptionPane.showInputDialog(null, "Booking Date:");
        int totalDay = Integer.parseInt(JOptionPane.showInputDialog(null, "Total Day of Stay:"));
        int roomNo = Integer.parseInt(JOptionPane.showInputDialog(null, "Room Number:"));
        char roomCategory = JOptionPane.showInputDialog(null, "Room Category:").charAt(0);
        boolean breakfast = Boolean.parseBoolean(JOptionPane.showInputDialog(null, "Breakfast Included [true/false]:"));
        String paymentDate = JOptionPane.showInputDialog(null, "Payment Date:");
        char paymentStatus = JOptionPane.showInputDialog(null, "Payment Status:").charAt(0);
        String paymentMethod = JOptionPane.showInputDialog(null, "Payment Method:");

        HotelBooking newBooking = new HotelBooking(guestName, phoneNumber, numOfGuests, bookingID, bookingDate, totalDay,
                roomNo, roomCategory, breakfast, paymentDate, paymentStatus, paymentMethod);

        hotelBookings.add(newBooking);
        JOptionPane.showMessageDialog(null, "Booking added successfully.");
    }
    
	//Method to remove booking by using booking ID
    public static void removeBooking(java.util.LinkedList<HotelBooking> hotelBookings) {
        JOptionPane.showMessageDialog(null, "---- Remove Booking ----");

        int bookingID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Booking ID to remove:"));

        boolean removed = false;

        for (HotelBooking booking : hotelBookings) {
            if (booking.getBookingID() == bookingID) {
                hotelBookings.remove(booking);
                removed = true;
                JOptionPane.showMessageDialog(null, "Booking removed successfully.");
                break;
            }
        }

        if (!removed) {
            JOptionPane.showMessageDialog(null, "Booking ID not found.");
        }
    }

	//Method to display all the unpaid bookings
    public static void displayUnpaidBookings(java.util.LinkedList<HotelBooking> hotelBookings) {
        JOptionPane.showMessageDialog(null, "--------- Unpaid Bookings ---------");

        boolean found = false;
        StringBuilder unpaidBookings = new StringBuilder();

        for (HotelBooking booking : hotelBookings) {
            if (booking.getPaymentStatus() == 'N') {
                unpaidBookings.append(booking).append("\n\n");
                found = true;
            }
        }

        if (!found) {
            unpaidBookings.append("No unpaid bookings found.");
        }

        JTextArea textArea = new JTextArea(unpaidBookings.toString(), 20, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12)); // Set the font to a fixed-width font

        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Unpaid Bookings", JOptionPane.PLAIN_MESSAGE);
    }
    
    //Method to display all the paid bookings
    public static void displayPaidBookings(java.util.LinkedList<HotelBooking> hotelBookings) {
        JOptionPane.showMessageDialog(null, "--------- Paid Bookings ---------");

        boolean found = false;
        StringBuilder paidBookings = new StringBuilder();

        for (HotelBooking booking : hotelBookings) {
            if (booking.getPaymentStatus() == 'D') {
                paidBookings.append(booking).append("\n\n");
                found = true;
            }
        }

        if (!found) {
            paidBookings.append("No paid bookings found.");
        }

        JTextArea textArea = new JTextArea(paidBookings.toString(), 20, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12)); // Set the font to a fixed-width font

        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Paid Bookings", JOptionPane.PLAIN_MESSAGE);
    }

	//Method to display all the upcoming bookings
    public static void displayUpcomingBookings(java.util.LinkedList<HotelBooking> hotelBookings) {
        JOptionPane.showMessageDialog(null, "-------- Upcoming Bookings --------");

        boolean found = false;
        String currentDate = getCurrentDate();

        // Format the specified date '20230707' for comparison
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate specifiedDate = LocalDate.parse("20230707", formatter);

        StringBuilder upcomingBookings = new StringBuilder();

        for (HotelBooking booking : hotelBookings) {
            LocalDate bookingDate = LocalDate.parse(booking.getBookingDate(), formatter);
            // Check if booking date is after the specified date '20230707'
            if (bookingDate.isAfter(specifiedDate)) {
                upcomingBookings.append(booking).append("\n\n");
                found = true;
            }
        }

        if (!found) {
            upcomingBookings.append("No upcoming bookings found.");
        }

        JTextArea textArea = new JTextArea(upcomingBookings.toString(), 20, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12)); // Set the font to a fixed-width font

        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Upcoming Bookings", JOptionPane.PLAIN_MESSAGE);
    }

	//Method to update the payment status
    public static void updatePaymentStatus(java.util.LinkedList<HotelBooking> hotelBookings) {
        JOptionPane.showMessageDialog(null, "---- Update Payment Status ----");

        int bookingID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Booking ID to update payment status:"));

        boolean found = false;

        for (HotelBooking booking : hotelBookings) {
            if (booking.getBookingID() == bookingID) {
                char paymentStatus = JOptionPane.showInputDialog(null, "Enter the new Payment Status (D or N):").toUpperCase()
                        .charAt(0);

                booking.setPaymentStatus(paymentStatus);
                JOptionPane.showMessageDialog(null, "Payment status updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Booking ID not found.");
        }
    }

	//Method to calculate the total payments 
    public static void calculateTotalPayments(java.util.LinkedList<HotelBooking> hotelBookings) {
        JOptionPane.showMessageDialog(null, "----- Calculate Total Payments -----");

        int totalPaymentD = 0; // Total payments received from category D
        int totalPaymentN = 0; // Total payments received from category N

        for (HotelBooking booking : hotelBookings) {
            if (booking.getPaymentStatus() == 'D') {
                double totalPayment = booking.calculateTotalPayment();
                totalPaymentD += totalPayment;
            } else {
                double totalPayment = booking.calculateTotalPayment();
                totalPaymentN += totalPayment;
            }
        }
        JOptionPane.showMessageDialog(null, "Total Payment Received (D): RM " + totalPaymentD + "\nTotal Payment Received (N): RM " + totalPaymentN);
    }

	//Method to format the date
    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return currentDate.format(formatter);
    }

	//Method to update the booking file
    public static void updateBookingFile(java.util.LinkedList<HotelBooking> hotelBookings) {
        try {
            PrintWriter writer = new PrintWriter(BOOKING_FILE_PATH);

            for (HotelBooking booking : hotelBookings) {
                writer.println(booking.toString());
            }

            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating booking file: " + e.getMessage());
      }
    }

    // LinkedList class to manage the linked list of HotelBooking nodes
    public static class HotelBookingLinkedList {
        private Node head; // Head of the linked list

        public HotelBookingLinkedList() {
            head = null;
        }

        // Method to add a new HotelBooking node to the linked list
        public void addBooking(HotelBooking newBooking) {
            Node newNode = new Node(newBooking);
            if (head == null) {
                head = newNode;
            } else {
                Node currentNode = head;
                while (currentNode.next != null) {
                    currentNode = currentNode.next;
                }
                currentNode.next = newNode;
            }
        }

        // Method to remove a HotelBooking node from the linked list based on booking ID
        public void removeBooking(int bookingID) {
            if (head == null) {
                System.out.println("Linked list is empty.");
                return;
            }

            if (((HotelBooking) head.data).getBookingID() == bookingID) {
                head = head.next;
                return;
            }

            Node prevNode = head;
            Node currentNode = head.next;
            while (currentNode != null) {
            if (((HotelBooking) currentNode.data).getBookingID() == bookingID) {
                prevNode.next = currentNode.next;
                return;
            }
                prevNode = currentNode;
                currentNode = currentNode.next;
            }

            System.out.println("Booking ID not found.");
        }

        // Method to display all unpaid bookings in the linked list
        public void displayUnpaidBookings() {
            Node currentNode = head;
            boolean found = false;

            while (currentNode != null) {
                HotelBooking booking = (HotelBooking) currentNode.data;
                if (booking.getPaymentStatus() == 'N') {
                    System.out.println(booking);
                    found = true;
                }
                currentNode = currentNode.next;
             }

            if (!found) {
                System.out.println("No unpaid bookings found.");
            }
        }
    }
}
