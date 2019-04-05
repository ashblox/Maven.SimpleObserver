package entities;

import interfaces.Observer;
import interfaces.Subject;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Subject {
    
    private String name;
    private List<Observer> observers;
    private List<Message> messages;
    private User creator;

    public Channel() {
    }

    public Channel(String name) {
        this.name = name;
        this.observers = new ArrayList<Observer>();
        this.messages = new ArrayList<Message>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
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

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void alert() {
        for (Observer observer: observers) {
            observer.update(this);
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
