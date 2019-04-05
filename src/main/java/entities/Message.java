package entities;

import java.util.Date;

public class Message {

    private String contents;
    private User sender;
    private Channel channel;
    private Date timestamp;

    public Message(String contents) {
        this.contents = contents;
        this.timestamp = new Date();
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
