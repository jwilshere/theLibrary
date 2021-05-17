
public class HelpMethod {


    public User getAUser(int id){
        Getset object = new Getset();
        User anvandare = new User();

        for(User s: object.getUsers()) {
            if (s.getId() == id) {
                anvandare = s;
            }
        }
        return anvandare;
    }

    public void main(String[] args) {
        HelpMethod hej = new HelpMethod();
        System.out.println(hej.getAUser(1234));

    }

}
