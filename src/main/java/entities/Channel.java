package entities;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    private String name;
    private List<User> subscribers;
    private List<Message> messages;
    private User creator;

    public Channel() {
    }

    public Channel(String name) {
        this.name = name;
        this.subscribers = new ArrayList<User>();
        this.messages = new ArrayList<Message>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    public void addSubscriber(User user) {
        subscribers.add(user);
    }

    public void removeSubscriber(User user) {
        subscribers.remove(user);
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void alertAll() {
        for (User user: subscribers) {
            user.alert(this);
        }
    }

    public void clearAlerts() {
        // implementation will be added when we have a clear idea what this will look like
    }

    @Override
    public String toString() {
        return "entities.Channel{" +
                "name='" + name + '\'' +
                '}';
    }
}
