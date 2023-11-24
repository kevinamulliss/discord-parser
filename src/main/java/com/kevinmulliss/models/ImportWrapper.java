package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ImportWrapper {

    @SerializedName("guild")
    private Server server;
    @SerializedName("channel")
    private Channel channel;
    @SerializedName("dateRange")
    private DateRange dateRange;
    @SerializedName("exportedAt")
    private Date exportedAt;
    @SerializedName("messages")
    private List<Message> messages;
    @SerializedName("messageCount")
    private int messageCount;

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public Date getExportedAt() {
        return exportedAt;
    }

    public void setExportedAt(Date exportedAt) {
        this.exportedAt = exportedAt;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }
}
