package entities;

import interfaces.Observer;

import javax.naming.OperationNotSupportedException;

public class User implements Observer {

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
        currentChannel.attach(this);
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
        // ideally the creator would see the channels name as the recipient and the recipient would see
        // the channel's name as the sender; not sure how to implement this
        Channel channel = new Channel(userName + " & " + recipient.getUserName());
        channel.attach(this);
        channel.attach(recipient);
        currentChannel = channel;
        postInChannel(contents);
    }

    public void postInChannel(String contents) {
        Message message = new Message(contents);
        message.setSender(this);
        currentChannel.addMessage(message);
        currentChannel.alert();
        System.out.println("ran");
    }

    public void selectChannel(Channel channel) {
        currentChannel = channel;
        channel.clearAlerts();
    }

    public void createChannel(String name, User... users) {
        Channel channel = new Channel(name);
        channel.attach(this);
        for (User user : users) {
            channel.attach(user);
        }
        channel.setCreator(this);
        currentChannel = channel;
    }

    public void unsubscribe(Channel channel) {
        channel.detach(this);
    }

    public void addUser(Channel channel, User... users) throws OperationNotSupportedException {
        if (channel.getCreator() == this) {
            for (User user: users) {
                channel.attach(user);
            }
        } else {
            throw new OperationNotSupportedException("Only a channel administrator can add users");
        }
    }

    public void removeUser(Channel channel, User... users) throws OperationNotSupportedException {
        if (channel.getCreator() == this) {
            for (User user: users) {
                channel.detach(user);
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

    public void update(Object o) {
        Channel channel = (Channel)o;
        if (currentChannel != channel) {
            System.out.println(userName + ": there are new messages in " + channel.getName());
            // this will ultimately get replaced with something else
        }
    }
}
