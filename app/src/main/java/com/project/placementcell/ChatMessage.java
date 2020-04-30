package com.project.placementcell;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class ChatMessage {

    private String messageText;
    private String messageUser;
    private long messageTime;
    FirebaseAuth auth;

    public ChatMessage(String messageText) {
        this.messageText = messageText;
        this.messageUser =FirebaseAuth.getInstance()
                .getCurrentUser()
                .getUid();
        ;

        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public ChatMessage(){

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}