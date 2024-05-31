package com.kevinmulliss.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class DiscordMessage extends Message {
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private MessageTypeEnum type;
    @SerializedName("timestampEdited")
    private Date timestampEdited;
    @SerializedName("callEndedTimestamp")
    private Date callEndedTimestamp;
    @SerializedName("isPinned")
    private boolean isPinned;
    @SerializedName("attachments")
    private List<Attachment> attachments;
    @SerializedName("embeds")
    private List<Embed> embeds;
    @SerializedName("stickers")
    private List<Sticker> stickers;
    @SerializedName("reactions")
    private List<Reaction> reactions;
    @SerializedName("mentions")
    private List<Author> mentions;

    public DiscordMessage(Date timestamp, String content, Author author) {
        super(timestamp, content, author);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MessageTypeEnum getType() {
        return type;
    }

    public void setType(MessageTypeEnum type) {
        this.type = type;
    }

    public Date getTimestampEdited() {
        return timestampEdited;
    }

    public void setTimestampEdited(Date timestampEdited) {
        this.timestampEdited = timestampEdited;
    }

    public Date getCallEndedTimestamp() {
        return callEndedTimestamp;
    }

    public void setCallEndedTimestamp(Date callEndedTimestamp) {
        this.callEndedTimestamp = callEndedTimestamp;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<Embed> getEmbeds() {
        return embeds;
    }

    public void setEmbeds(List<Embed> embeds) {
        this.embeds = embeds;
    }

    public List<Sticker> getStickers() {
        return stickers;
    }

    public void setStickers(List<Sticker> stickers) {
        this.stickers = stickers;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public List<Author> getMentions() {
        return mentions;
    }

    public void setMentions(List<Author> mentions) {
        this.mentions = mentions;
    }
}