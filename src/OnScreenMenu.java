/*
 * Name : Ashbel Legros
 * Course : CEN 4333C
 * Date : 06/18/26
 * Class : OnScreenMenu
 * Purpose : Loads the intial txt file in order to display the action menu for the LMS. User provides path for the
 * formatted text file. Further, user provides a number for the corresponding action they want executed.
 * */
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OnScreenMenu extends PatronManager{
    private Scanner inputReader = new Scanner(System.in);

    public Scanner getInputReader() {
        return inputReader;
    }

    public void setInputReader(Scanner inputReader) {
        this.inputReader = inputReader;
    }

    /* Method: loadFile()
       Purpose: Handles reading a file path to open up a text file provided by the user. The text file is then
       read and used to create data for the PatronManager service. This serves as the startup for the program.
       Return Type: PatronManager
     */
    public PatronManager loadFile() {

        while (true) {
            System.out.println("Please enter the path of your txt file (0 to exit): ");
            String filePath = inputReader.nextLine();

            if (filePath.equals("0")) {
                System.exit(0);
            }

            PatronManager patronManager = new PatronManager();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                System.out.println("Loading file...\n");

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split("-");

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String address = data[2];
                    double overDueAmount = Double.parseDouble(data[3]);

                    System.out.println(id + "-" + name + "-" + address + "-" + overDueAmount);

                    Patron patron = new Patron(id, name, address, overDueAmount);
                    patronManager.addPatron(patron);
                }
                System.out.println();
                System.out.println("File successfully loaded.\n");
                return patronManager;

            } catch (FileNotFoundException e) {
                System.out.println("Could not locate the file\n");
                inputReader = new Scanner(System.in);
            } catch (IOException e) {
                System.out.println("Something went wrong!\n");
            }
        }
    }

    /* Method: displayMenu
       Purpose: Shows onscreen menu which prompts user to issue a command. Commands are ran from the PatronManager
       service.
       Return Type: boolean
     */
    public void displayMenu(PatronManager patronManager){
        while (true) {
            System.out.println("Welcome to the Patron Manager System\n" +
                    "---------------------------------------");

            System.out.println("1 - Add Patron");
            System.out.println("2 - Display Patrons");
            System.out.println("3 - Remove Patron");
            System.out.println("0 - Exit");
            System.out.println("------------------------------------");


            int commandNumber;
            try{
                System.out.print("Enter your command number: ");
                commandNumber = inputReader.nextInt();
                inputReader.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println("Please enter a valid command number\n");
                inputReader.nextLine();
                continue;
            }

            switch(commandNumber){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    patronManager.addPatron(patronManager.addPatronManually());
                    patronManager.displayPatrons();
                    break;
                case 2:
                    patronManager.displayPatrons();
                    break;
                case 3:
                    patronManager.removePatron();
                    patronManager.displayPatrons();
                    break;
                default:
                    System.out.println("Something went wrong!\n");
                    break;
            }
        }
    }

}
