package com.livingworld.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dizzay on 12/29/2017.
 */

public class MessageServiceModel {

    @SerializedName("chat_room_id")
    @Expose
    private String chatRoomId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("message_id")
    @Expose
    private Integer messageId;
    @SerializedName("message")
    @Expose
    private String message;

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
