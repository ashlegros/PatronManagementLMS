/*
 * Name : Ashbel Legros
 * Course : CEN 4333C
 * Date : 06/19/26
 * Class : MainLMS
 * Program Objective : Provide Librarian with a LMS to manage patrons loaded by a text file. They should be able to
 * print all current patrons to screen, add a new patron with details, and remove a patron. User should be able to exit
 * the LMS as well using the corresponding command action.
 * */
public class MainLMS {
    public static void main(String[] args) {
        OnScreenMenu onScreenMenu = new OnScreenMenu();

        onScreenMenu.displayMenu(onScreenMenu.loadFile());
    }
}
