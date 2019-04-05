package entities;

public class Administrator extends User {

    private static final User INSTANCE = new Administrator();

    private Administrator() {
        super.setUserName("administrator");
    }

    public static User getInstance() {
        return INSTANCE;
    }
}
