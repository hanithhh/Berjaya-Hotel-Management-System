# Hotel-Management-System

public class HotelBooking {
	
    // ATTRIBUTES
    private String guestName;
    private String phoneNum;
    private int numOfGuest;
    private int bookingID;
    private String bookingDate;
    private int totalDay;
    private int roomNo;
    private char roomCategory;
    private boolean breakfast;
    private String paymentDate;
    private char paymentStatus;
    private String paymentMethod;

    // NORMAL CLASS
    public HotelBooking(String gn, String pn, int ng, int bi, String bd, 
    	int td, int r, char rc, boolean b, String pd, char ps, String pm) {
        guestName = gn;
        phoneNum = pn;
        numOfGuest = ng;
        bookingID = bi;
        bookingDate = bd;
        totalDay = td;
        roomNo = r;
        roomCategory = rc;
        breakfast = b;
        paymentDate = pd;
        paymentStatus = ps;
        paymentMethod = pm;
    }

    // SETTERS
    public void setGuestName(String gn) {
        guestName = gn;
    }

    public void setPhoneNum(String pn) {
        phoneNum = pn;
    }

    public void setNumOfGuest(int ng) {
        numOfGuest = ng;
    }

    public void setBookingID(int bi) {
        bookingID = bi;
    }

    public void setBookingDate(String bd) {
        bookingDate = bd;
    }

    public void setTotalDay(int td) {
        totalDay = td;
    }

    public void setRoomNo(int r) {
        roomNo = r;
    }

    public void setRoomCategory(char rc) {
        roomCategory = rc;
    }

    public void setBreakfast(boolean b) {
        breakfast = b;
    }

    public void setPaymentDate(String pd) {
        paymentDate = pd;
    }

    public void setPaymentStatus(char ps) {
        paymentStatus = ps;
    }

    public void setPaymentMethod(String pm) {
        paymentMethod = pm;
    }

    // GETTERS
    public String getGuestName() {
        return guestName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getNumOfGuest() {
        return numOfGuest;
    }

    public int getBookingID() {
        return bookingID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public int getTotalDay() {
        return totalDay;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public char getRoomCategory() {
        return roomCategory;
    }

    public boolean hasBreakfast() {
        return breakfast;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public char getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    // PROCESSOR
    public double calculateTotalPayment() {
        double roomPrice;
        
        if (roomCategory == 'S') {
            roomPrice = 350;
        } else if (roomCategory == 'P') {
            roomPrice = 600;
        } else {
            roomPrice = 1350;
        }
        
        double totalPayment = roomPrice * totalDay;
        
        if (breakfast) {
            totalPayment += numOfGuest * 30;
        }
        
        return totalPayment;
    }

    // PRINTER
    public String toString() {
        return  "-----------------------------------" + "\n" +
        		"	  Hotel Booking Details	        " + "\n" +
        		"-----------------------------------" + "\n" +
                " Guest Name	   : " + guestName + "\n" +
                " Phone Number	   : " + phoneNum + "\n" +
                " Number of Guest  : " + numOfGuest + "\n" +
                " Booking ID	   : " + bookingID + "\n" +
                " Booking Date	   : " + bookingDate + "\n" +
                " Total Day of Stay: " + totalDay + "\n" +
                " Room Number	   : " + roomNo + "\n" +
                " Room Category	   : " + roomCategory + "\n" +
                " Breakfast?	   : " + breakfast + "\n" +
                " Payment Date	   : " + paymentDate + "\n" +
                " Payment Status   : " + paymentStatus + "\n" +
                " Payment Method   : " + paymentMethod + "\n" +
                " Total Payment	   : RM " + calculateTotalPayment() + "\n" +
                "-----------------------------------";
    }
}
