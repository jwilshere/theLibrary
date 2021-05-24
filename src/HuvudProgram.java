import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class HuvudProgram {

    public static void main(String[] args) {

        Getset gs = new Getset();
        HelpMethod hm = new HelpMethod(gs);
        AdminInteraction ai = new AdminInteraction(gs);
        Scanner scan = new Scanner(System.in);

        int val = 0;
        boolean fortsatt = false;
        gs.connectDB();

        do {
            System.out.println("Välkommen till Tygelbys Bibliotek");
            System.out.println("¨¨¨¨¨¨¨¨¨¨¨ MENY ¨¨¨¨¨¨¨¨¨¨¨");
            System.out.println(" 1 - Sök på en bok");
            System.out.println(" 2 - Låna en bok");
            System.out.println(" 3 - Lämna tillbaka en bok");
            System.out.println(" 4 - Kolla om användare får låna");
            System.out.println(" 5 - Visa alla böcker");
            System.out.println(" 6 - Lägg till bok");
            System.out.println(" 7 - Ta bort bok");
            System.out.println(" 8 - Lägg till ny användare");
            System.out.println(" 9 - Ta bort användare");
            System.out.println(" 0 - Exit");
            System.out.println("-----------------------------");
            System.out.println("Skriv in en siffra och tryck enter:");


            while (!fortsatt) {

                val = Integer.parseInt(scan.nextLine());
                if (val <= 9) {
                    fortsatt = true;
                } else {
                    System.out.println("Välj ett nummer mellan 0 - 9");
                    System.out.println("Skriv in ett nummer: ");
                }
            }


            switch (val) {

                case 1:
                    //System.out.println("Skriv in titel på boken:");
                    //String title = scan.nextLine();
                    System.out.println("Skriv in användarID:");
                    int userid = Integer.parseInt(scan.nextLine());

                    ai.checkIfSuspended(userid);//<--
                    fortsatt = false;
                    break;

                case 2:
                    System.out.println("Skriv in ISBN:");
                    int isbn = Integer.parseInt(scan.nextLine());
                    System.out.println("Skriv in användarID:");
                    int uid = Integer.parseInt(scan.nextLine());
                    ai.checkIfUserCanLend(isbn,uid); // <--
                    fortsatt = false;
                    break;

                case 3:
                    System.out.println("Skriv in användarID:");
                    int uiid = Integer.parseInt(scan.nextLine());

                    ai.suspendUser(uiid);//<--
                    fortsatt = false;
                    break;

                case 4:
                    System.out.println("Vill du visa alla böcker? Y/N");
                    char yOn = scan.nextLine().charAt(0);
                    if (yOn == 'Y' || yOn == 'y') {
                        ArrayList<Book> visa;
                        visa = hm.getBooks(); //<--
                        System.out.println();
                        System.out.println("---:Alla böcker:---");
                        for (Book b : visa) {
                            System.out.println(b.getTitle());

                        }
                        System.out.println();
                    }

                    fortsatt = false;
                    break;

                case 5:
                    System.out.println("Skriv in användarID:");
                    int uiiid = Integer.parseInt(scan.nextLine());
                    if (uiiid == hm.getAUser(uiiid).Id) {
                    System.out.println("Vill du verkligen radera användaren? Y/N");
                    char yOn1 = scan.nextLine().charAt(0);
                    if (yOn1 == 'Y' || yOn1 == 'y') {
                        try {
                        hm.deleteUser(uiiid); }

                        catch (IndexOutOfBoundsException i) {};

                        System.out.println("Användare med ID " + uiiid + " är borttagen");
                    }
                    else {
                        System.out.println("Användaren är inte borttagen");
                        }
                    }
                    else {
                    System.out.println("Användare existerar inte");
                }

                    fortsatt = false;
                    break;
                case 6:
                System.out.println("Skriv in boktitel:");
                try {
                String titel = scan.nextLine();
                if (hm.getISBNOnTitle(titel) > 0) {
                System.out.println(titel + " har ISBN: " + hm.getISBNOnTitle(titel)); }

                else {
                    System.out.println("Ingen bok med denna titel existerar");
                    }
                }
                catch (NumberFormatException n) {}

                fortsatt = false;
                break;
                case 7:
                    System.out.println("Skriv in boktitel:");
                    String titel1 = scan.nextLine();
                    System.out.println("Skriv in ISBN:");
                    int bid = Integer.parseInt(scan.nextLine());
                    System.out.println("Skriv in bokID:");
                    int isbn1 = Integer.parseInt(scan.nextLine());
                    try {
                       gs.addBook(bid,titel1,isbn1);
                        System.out.println();
                    }
                    catch (Exception n) {}

                    fortsatt = false;
                    break;
            }

        }
        while (val != 0);
        {
            System.out.println("Välkommen åter!");
        }
    }
}
