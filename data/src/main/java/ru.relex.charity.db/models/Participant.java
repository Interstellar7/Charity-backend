package ru.relex.charity.db.models;

import java.time.Instant;

public class Participant {
    private int auctionId;
    private int userId;
    private String messageToGiver;
    private Instant submitAt;

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessageToGiver() {
        return messageToGiver;
    }

    public void setMessageToGiver(String messageToGiver) {
        this.messageToGiver = messageToGiver;
    }

    public Instant getSubmitAt() {
        return submitAt;
    }

    public void setSubmitAt(Instant submitAt) {
        this.submitAt = submitAt;
    }
}
