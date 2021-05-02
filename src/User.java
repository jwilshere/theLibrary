public class User {

    // Attribut "Type"
    // 1: Undergraduate, Limit= 3
    // 2: Postgraduate, Limit= 5
    // 3: PHD/candidate, Limit= 7
    // 4: Teacher/Professor, Limit= 10

    int Id;
    String Fnamn;
    String Lnamn;
    int PersonId;
    int Type;
    int ItemBorrowed;
    int BorrowLimit;
    int Active;
    int Delays;

    public User(){

    }

    public User(int id, String fnamn, String lnamn, int personId, int type, int itemBorrowed, int borrowLimit, int active, int delays) {
        this.Id = id;
        this.Fnamn = fnamn;
        this.Lnamn = lnamn;
        this.PersonId = personId;
        this.Type = type;
        this.ItemBorrowed = itemBorrowed;
        this.BorrowLimit = borrowLimit;
        this.Active = active;
        this.Delays = delays;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getItemBorrowed() {
        return ItemBorrowed;
    }

    public void setItemBorrowed(int itemBorrowed) {
        ItemBorrowed = itemBorrowed;
    }

    public int getBorrowLimit() {
        return BorrowLimit;
    }

    public void setBorrowLimit(int borrowLimit) {
        BorrowLimit = borrowLimit;
    }

    public int isActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public int getDelays() {
        return Delays;
    }

    public void setDelays(int delays) {
        Delays = delays;
    }


    public int requestForBook (String title, int id){
        int ISBN = 0;
        //Then, the system checks whether this member is an undergraduate,
        // a postgraduate, a PhD student/candidate, or a teacher (professor, etc.).
        // The number of library items that he/she has borrowed in the past
        // is being checked and then whether he or she has the permission to borrow a new one according
        // to the limitation applicable in each case (as described in the paragraph above).

        //FÖRST KOLLA IFALL DEN FÅR LÅNA; IFALL DEN FÅR SÅ RETURNERA TITELNAMNET
        return ISBN;
    }

    public void RegisterUser(String Fnamn, String Lnamn, int PersonID){
        //If the personal number was already registered, but the person had violated the regulation,
        // then a notice is informing the librarian that the registration is not allowed and the process ends without registration.

        //• If the personal number was already registered and he/she has not violated the regulations,
        // then nothing happens (i.e., a message is printed to the librarian that everything is already alright).

        //• If the personal number was not in the database, the candidate member’s information
        // (first name, last name, personal number) is registered, and the member gets a suitable unique ID.

        //Antingen läggas som en ny användare eller ej
    }




}

