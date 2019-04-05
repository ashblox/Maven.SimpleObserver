package entities;

import interfaces.Observer;

import java.util.ArrayList;

public class DefaultChannel extends Channel {

    private static final DefaultChannel INSTANCE = new DefaultChannel();

    private DefaultChannel() {
        super.setName("Welcome entities.Channel");
        super.setObservers(new ArrayList<Observer>());
        super.setMessages(new ArrayList<Message>());
        super.setCreator(Administrator.getInstance());
    }

    public static DefaultChannel getInstance() {
        return INSTANCE;
    }

}
