
package resource.booking.system;


public class bookingroom{
    public String Fname;
    public String Lname;
    public int Numofpeople;
    public String UserEmail;
    public String CurrentBooking;
    public String Refreshments;
    public String Resources;

    public bookingroom(String Fname, String Lname, int Numofpeople, String UserEmail, String CurrentBooking, String Refreshments, String Resources) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.Numofpeople = Numofpeople;
        this.UserEmail = UserEmail;
        this.CurrentBooking = CurrentBooking;
        this.Refreshments = Refreshments;
        this.Resources = Resources;
    }

    public String getRefreshments() {
        return Refreshments;
    }

    public void setRefreshments(String Refreshments) {
        this.Refreshments = Refreshments;
    }

    public String getResources() {
        return Resources;
    }

    public void setResources(String Resources) {
        this.Resources = Resources;
    }
   

    public String getCurrentBooking() {
        return CurrentBooking;
    }

    public void setCurrentBooking(String CurrentBooking) {
        this.CurrentBooking = CurrentBooking;
    }

   

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public int getNumofpeople() {
        return Numofpeople;
    }

    public void setNumofpeople(int Numofpeople) {
        this.Numofpeople = Numofpeople;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }
}
