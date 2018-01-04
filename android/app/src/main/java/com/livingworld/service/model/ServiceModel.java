package com.livingworld.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dizzay on 12/29/2017.
 */

public class ServiceModel {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("chat_room_id")
    @Expose
    private String chatRoomId;
    @SerializedName("message")
    @Expose
    private MessageServiceModel MessageServiceModel;
    @SerializedName("user")
    @Expose
    private UserServiceModel user;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public MessageServiceModel getMessage() {
        return MessageServiceModel;
    }

    public void setMessage(MessageServiceModel MessageServiceModel) {
        this.MessageServiceModel = MessageServiceModel;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}
