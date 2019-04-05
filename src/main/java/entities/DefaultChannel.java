package entities;

import java.util.ArrayList;

public class DefaultChannel extends Channel {

    private static final DefaultChannel INSTANCE = new DefaultChannel();

    private DefaultChannel() {
        super.setName("Welcome entities.Channel");
        super.setSubscribers(new ArrayList<User>());
        super.setMessages(new ArrayList<Message>());
        super.setCreator(Administrator.getInstance());
    }

    public static DefaultChannel getInstance() {
        return INSTANCE;
    }

}
