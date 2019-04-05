import entities.DefaultChannel;
import entities.User;

public class Main {

    public static void main(String[] args) {
        DefaultChannel.getInstance();
        User ashblox = new User("Ashley", "Bloxom", "ashblox");
        User caraeppes = new User("Cara", "Eppes", "caraeppes");
        User katebayard = new User("Kate", "Bayard", "kbayard");

        ashblox.postInChannel("Hi everyone");
        caraeppes.postInChannel("Hi!");
        katebayard.postInChannel("Hey you guys");

        caraeppes.sendPrivateMessage("So about the project", ashblox);

        katebayard.sendPrivateMessage("Yikes!", caraeppes);
    }

}