import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Huvudprogram {

    public static void main(String[] args) {

        Getset gs = new Getset();
        HelpMethod hm = new HelpMethod(gs);
        AdminInteraction ai = new AdminInteraction(gs);
        Scanner scan = new Scanner(System.in);

        int val = 0;
        int val2 = 0;
        int val3 = 0;
        boolean fortsatt = false;
        gs.connectDB();

        do {
            System.out.println("Välkommen till Tygelbys Bibliotek");
            System.out.println("¨¨¨¨¨¨¨¨¨¨¨ HUVUDMENY ¨¨¨¨¨¨¨¨¨¨¨");
            System.out.println(" 1 - Hantera böcker");
            System.out.println(" 2 - Hantera användare");
            System.out.println(" 3 - Återlämna bok");
            System.out.println(" 4 - Kolla om användare får låna");
            System.out.println(" 5 - Visa alla försenade böcker");
           /* System.out.println(" 6 - ");
            System.out.println(" 7 - ");
            System.out.println(" 8 - ");
            System.out.println(" 9 - ");
            */
            System.out.println(" 0 - Exit");
            System.out.println("-----------------------------");
            System.out.println("Skriv in en siffra och tryck enter:");
            boolean fortsatt2 = false;
            boolean fortsatt3 = false;

            while (!fortsatt) {
                String gedd;
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
                    do {
                        System.out.println();
                        System.out.println("¨¨¨¨¨¨¨¨¨¨¨ Hantera böcker ¨¨¨¨¨¨¨¨¨¨¨");
                        System.out.println(" 1 - Lägg till ny bok");
                        System.out.println(" 2 - Radera en bok");
                        System.out.println(" 3 - Hämta ISBN på boktitel");
                        System.out.println(" 4 - Visa alla böcker");
                        System.out.println(" 0 - Gå till huvudmeny");

                        while (!fortsatt2) {

                            val2 = Integer.parseInt(scan.nextLine());

                            if (val2 <= 4) {
                                fortsatt2 = true;
                            } else {
                                System.out.println("Välj ett nummer mellan 0 - 4");
                                System.out.println("Skriv in ett nummer: ");
                            }
                        }
                        switch (val2) {
                            case 1: // Lägg till ny bok
                                System.out.println("Skriv in boktitel:");
                                String titel1 = scan.nextLine();
                                System.out.println("Skriv in bokId:");
                                int bid = Integer.parseInt(scan.nextLine());
                                System.out.println("Skriv in ISBN:");
                                int isbn1 = Integer.parseInt(scan.nextLine());
                                try {
                                    gs.addBook(bid,titel1,isbn1);
                                    System.out.println();
                                }
                                catch (Exception n) {}

                                fortsatt2 = false;
                                break;
                            case 2: // Radera bok
                                System.out.println("Skriv in bokID:");
                                int bokid = Integer.parseInt(scan.nextLine());
                                System.out.println("Vill du radera bok med ID: " + bokid + " Y/N ?");
                                char yOn2 = scan.nextLine().charAt(0);
                                if (yOn2 == 'Y' || yOn2 == 'y') {
                                    gs.deleteBook(bokid);
                                    System.out.println();
                                    System.out.println();
                                }
                                else {
                                    System.out.println("Bok ej borttagen");
                                }

                                fortsatt2 = false;
                                break;
                            case 3: // Sök på boktitel
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

                                fortsatt2 = false;
                                break;
                            case 4: // Visa alla böcker
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

                                fortsatt2 = false;
                                break;

                        }

                    }
                    while (val2 != 0);
                    fortsatt = false;
                    break;

                case 2:
                    do {
                        System.out.println();
                        System.out.println("¨¨¨¨¨¨¨¨¨¨¨ Hantera användare ¨¨¨¨¨¨¨¨¨¨¨");
                        System.out.println(" 1 - Lägg till ny användare");
                        System.out.println(" 2 - Radera en användare");
                        System.out.println(" 3 - Suspendera en användare");
                        System.out.println(" 4 - Kolla om en användare är suspenderad");
                        System.out.println(" 5 - Uppdatera sen inlämning på användare");
                        System.out.println(" 0 - Gå till huvudmeny");

                        while (!fortsatt3) {
                            val3 = Integer.parseInt(scan.nextLine());
                            if (val3 <= 5) {
                                fortsatt3 = true;
                            } else {
                                System.out.println("Välj ett nummer mellan 0 - 4");
                                System.out.println("Skriv in ett nummer: ");
                            }
                        }
                        switch (val3) {
                            case 1: //
                                System.out.println("Ange förnamn:");
                                String fnamn = scan.nextLine();
                                System.out.println("Ange efternamn:");
                                String lnamn = scan.nextLine();
                                System.out.println("Ange personnummer:");
                                int pid = Integer.parseInt(scan.nextLine());
                                System.out.println("Ange typ:");
                                int typ = Integer.parseInt(scan.nextLine());
                                ai.RegisterUser(fnamn, lnamn, pid, typ);

                                fortsatt3 = false;
                                break;
                            case 2: //
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
                                fortsatt3 = false;
                                break;
                            case 3: // suspend user
                                System.out.println("Skriv in användarID:");
                                int busig = Integer.parseInt(scan.nextLine());
                                ai.suspendUser(busig);

                                fortsatt3 = false;
                                break;
                            case 4: // kolla om användare är susp
                                System.out.println("Skriv in användarID: ");
                                int banvID = Integer.parseInt(scan.nextLine());
                                ai.checkIfSuspended(banvID);
                                fortsatt3 = false;
                                break;
                            case 5: // Öka en delay på användare
                                System.out.println("Skriv in användarID: ");
                                int banID = Integer.parseInt(scan.nextLine());
                                hm.updateDelays(banID);
                                fortsatt3 = false;
                                break;
                        }

                    }
                    while (val3 != 0);
                    fortsatt = false;
                    break;

                case 3: //återlämna bok
                    System.out.println("Skriv in användarID:");
                    int libid = Integer.parseInt(scan.nextLine());
                    System.out.println("Skriv in bokID:");
                    int klibid = Integer.parseInt(scan.nextLine());

                    //<--
                    fortsatt = false;
                    break;

                case 4:
                    System.out.println("Skriv in ISBN:");
                    int isbn = Integer.parseInt(scan.nextLine());
                    System.out.println("Skriv in användarID:");
                    int uid = Integer.parseInt(scan.nextLine());
                    ai.checkIfUserCanLend(isbn,uid); // <--
                    fortsatt = false;
                    break;

                case 5:

                    fortsatt = false;
                    break;
                case 6:
                /*System.out.println("Skriv in boktitel:");
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

                 */
                case 7:
                   /* System.out.println("Skriv in boktitel:");
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

                    */
                case 8:
                    //System.out.println("Skriv in titel på boken:");
                    //String title = scan.nextLine();
                    //System.out.println("Skriv in användarID:");
                    //int userid = Integer.parseInt(scan.nextLine());

                    //ai.checkIfSuspended(userid);//<--
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

