/*
 * Name : Ashbel Legros
 * Course : CEN 4333C
 * Date : 06/18/26
 * Class : PatronManager
 * Purpose : Handles modifications/actions to patron records within the LMS.
 * */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PatronManager extends Patron{
    private ArrayList<Patron> patrons;

    public PatronManager(){
        patrons = new ArrayList<>();
    }

    public ArrayList<Patron> getPatrons() {
        return patrons;
    }

    public void setPatrons(ArrayList<Patron> patrons) {
        this.patrons = patrons;
    }

    /* Method: addPatron()
       Purpose: adds a patron object to the patron list
       Arguments: Patron object
       Return Type: void
     */
    public void addPatron(Patron patron){
        patrons.add(patron);
    }

    /* Method: removePatron()
       Purpose: Asks for user input to enter an id number. The number is used to find the patron within the list
       and remove them from the list.
       Return Type: boolean
     */
    public void removePatron(){
        Scanner input = new Scanner(System.in);

        System.out.print("\nPlease enter the ID of the patron you want to remove: ");
        int id = input.nextInt();

        for(int i = 0; i < patrons.size(); i++){
            Patron patronToBeRemoved = patrons.get(i);
            if (patronToBeRemoved.getPatronID() == id){
                patrons.remove(i);
                System.out.println("Patron ID: " + patronToBeRemoved.getPatronID() + " has been found and removed.\n");
            }
            else {
                System.out.println("Looking for patron in spot: " + (i + 1));
                if(i == patrons.size()-1){
                    System.out.println("Patron ID: " + id + " has not been found.\n");
                }
            }
        }
        System.out.println("Updated Patron List:\n" +
                "--------------------");
    }

    /* Method: addPatronManually
       Purpose: Asks for user input to enter each field of a patron. Input is used to create a new patron with manually
       entered information. Used in tandem with addPatron() to add to the newly created Patron to the list.
       Return Type: Patron
     */
    public Patron addPatronManually(){
        Patron patron = new Patron();
        Scanner input = new Scanner(System.in);

        while (!patron.idHasSevenDigits() || !idIsUnique(patron.getPatronID())){
            System.out.print("\nEnter a seven digit Patron ID: ");
            try {
                patron.setPatronID(input.nextInt());
            }
            catch (InputMismatchException e){
                System.out.println("Please enter a 7 digit number");
            }
            input.nextLine();
        }

        System.out.print("Enter Patron Name: ");
        patron.setName(input.nextLine());

        System.out.print("Enter Patron Address: ");
        patron.setAddress(input.nextLine());

        while (!patron.overdueAmountIsInRange(patron.getOverdueAmount())){
            System.out.print("Enter Patron Overdue Fine Amount, must be between $0-$250: ");
            try {
                patron.setOverdueAmount(input.nextDouble());
            }
            catch (InputMismatchException e){
                System.out.println("Please enter a valid dollar amount");
            }
        }

        System.out.println("Patron has been recorded!\n");

        System.out.println("Updated Patron List:\n" +
                "--------------------");

        return patron;
    }

    /* Method: displayPatrons()
       Purpose: displays all patrons stored line by line separated by +'s in between lines.
       Return Type: void
     */
    public void displayPatrons(){
        System.out.println("\nYour Patrons\n" +
                "--------------");
        for (Patron patron : patrons){
            System.out.print(patron.getPatronID() + "-" + patron.getName() + "-" + patron.getAddress()
            + "- ");
            System.out.printf("%.2f\n",patron.getOverdueAmount());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        System.out.println("\n");
    }

    /* Method: idIsUnique()
       Purpose: Checks if patrons' id is not taken by a current patron
       Return Type: boolean
     */
    public boolean idIsUnique(int patronID){
        for (Patron patron : patrons){
            if (patron.getPatronID() == patronID){
                return false;
            }
        }
        return true;
    }
}
