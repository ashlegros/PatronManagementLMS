/*
* Name : Ashbel Legros
* Course : CEN 4333C
* Date : 06/18/26
* Class : Patron
* Purpose : Represent a patron as a record/object in the LMS.
* */
public class Patron {
    private int patronID = 0;
    private String name;
    private String address;
    private double overdueAmount = -1.0;

    public Patron(int patronID, String name, String address, double overdueAmount) {
        this.patronID = patronID;
        this.name = name;
        this.address = address;
        this.overdueAmount = overdueAmount;
    }

    public Patron() {
    }

    public int getPatronID() {
        return patronID;
    }

    public void setPatronID(int patronID) {
        this.patronID = patronID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(double overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    /* Method: idHasSevenDigits
       Purpose: Checks if patrons' id has seven digits
       Return Type: boolean
     */
    public boolean idHasSevenDigits() {
        int numDigits = String.valueOf(getPatronID()).length();
        if (numDigits != 7) {
            return false;
        }
        return true;
    }

    /* Method: overdueAmountIsInRange
       Purpose: Checks if overdue fine amount is within $0 - $250
       Return Type: boolean
     */
    public boolean overdueAmountIsInRange(double amount) {
        if (amount < 0 || amount > 250) {
            return false;
        }
        return true;
    }

}
