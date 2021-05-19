import java.sql.Date;

public class AdminInteraction {
    HelpMethod object;
    Getset getSet;

    AdminInteraction(){

    }

    AdminInteraction(Getset HM){
        getSet = HM;
    }

    public void addUser(User User){
        //select statement som kollar ifall
    }

    public void deleteUser(){
    }

    public void checkIfUserDelayed(int id){

    }

    public void checkIfBookIsAvaible(int ISBN)  { //inparametern blir en metod (requestForBook)

        //Afterward, the librarian is looking for the library title with the ISBN (an integer of 6 digits, e.g., 238103):
        //• If it does not exist then the member is notified (i.e., you should print a message to the librarian who uses the system).

        //• If the library title is found, then the librarian checks if there is an available item for borrowing
        // and in case there is not an available item you notify the librarian (i.e., you should print a message to the librarian who uses the system).

        //• If there is an available item, then the item is given to the member,
        // and the system updates the number of the member’s borrowed items appropriately and the available items of this title.
    }

    public void ReturnBook (int PersonID, int bookId){
        //Kolla ifall usern finns, ta bort antal lånade items på usern
        //Uppdatera i BookRegister att en bok av denna titel är tillgänglig
    }

    public void RegisterUser(String Fnamn, String Lnamn, int PersonID, int Typ){
        getSet = new Getset();
        object = new HelpMethod();

        for(User s: object.getUsers()){
            if(s.getPersonId() == PersonID){
                System.out.println("Användare är redan registrerad!");
            }
            if(s.getSuspendDate() != null){
                System.out.println("Användare är suspenderad");
            }
        }

        int Id = object.generateUserId();
        getSet.setUser(Id, Fnamn, Lnamn, PersonID, Typ, 0, Typ, 1,0, null);

        //If the personal number was already registered, but the person had violated the regulation,
        // then a notice is informing the librarian that the registration is not allowed and the process ends without registration.

        //• If the personal number was already registered and he/she has not violated the regulations,
        // then nothing happens (i.e., a message is printed to the librarian that everything is already alright).

        //• If the personal number was not in the database, the candidate member’s information
        // (first name, last name, personal number) is registered, and the member gets a suitable unique ID.

        //Antingen läggas som en ny användare eller ej
    }

    public static void main(String[] args) {
        AdminInteraction hej = new AdminInteraction();
        hej.RegisterUser("Martin", "Nilssn", 880528, 3);

    }

}
