package entities;

import javax.naming.OperationNotSupportedException;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private boolean isActive;
    private Channel currentChannel;

    public User() {
    }

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.isActive = true;
        this.currentChannel = DefaultChannel.getInstance();
        currentChannel.addSubscriber(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void sendPrivateMessage(String contents, User recipient) {
        Channel channel = new Channel(recipient.getUserName());
        channel.addSubscriber(this);
        channel.addSubscriber(recipient);
        currentChannel = channel;
        postInChannel(contents);
    }

    public void postInChannel(String contents) {
        Message message = new Message(contents);
        message.setSender(this);
        currentChannel.addMessage(message);
        currentChannel.alertAll();
        System.out.println("ran");
    }

    public void selectChannel(Channel channel) {
        currentChannel = channel;
        channel.clearAlerts();
    }

    public void createChannel(String name, User... users) {
        Channel channel = new Channel(name);
        channel.addSubscriber(this);
        for (User user : users) {
            channel.addSubscriber(user);
        }
        channel.setCreator(this);
        currentChannel = channel;
    }

    public void unsubscribe(Channel channel) {
        channel.removeSubscriber(this);
    }

    public void addUser(Channel channel, User... users) throws OperationNotSupportedException {
        if (channel.getCreator() == this) {
            for (User user: users) {
                channel.addSubscriber(user);
            }
        } else {
            throw new OperationNotSupportedException("Only a channel administrator can add users");
        }
    }

    public void removeUser(Channel channel, User... users) throws OperationNotSupportedException {
        if (channel.getCreator() == this) {
            for (User user: users) {
                channel.removeSubscriber(user);
            }
        } else {
            throw new OperationNotSupportedException("Only a channel administrator can remove users");
        }
    }

    public void editMessage(Message message, String newContents) throws OperationNotSupportedException {
        if (message.getSender() == this) {
            message.setContents(newContents);
        } else {
            throw new OperationNotSupportedException("Only message creator can edit message");
        }
    }

    public void deleteMessage(Message message) throws OperationNotSupportedException {
        if (message.getSender() == this) {
            message.getChannel().removeMessage(message);
        } else {
            throw new OperationNotSupportedException("Only message creator can delete message");
        }
    }

    public void alert(Channel channel) {
        if (currentChannel != channel) {
            System.out.println(userName + ": there are new messages in " + channel.getName());
            // this will ultimately get replaced with something else
        }
    }
}
