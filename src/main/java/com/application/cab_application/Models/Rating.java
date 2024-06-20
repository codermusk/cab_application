package com.application.cab_application.Models;

public class Rating {
    private int id;
    private String comments;
    private double ratingValue;
    private int rideID;

    public Rating() {
    }

    public int getId() {
        return id;
    }

    public String getComments() {
        return comments;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public int getRideID() {
        return rideID;
    }

    public Rating(int rideID, double ratingValue, String comments) {
        this.rideID = rideID;
        this.ratingValue = ratingValue;
        this.comments = comments;
    }

    public Rating(int id, String comments, double ratingValue, int rideID) {
        this.id = id;
        this.comments = comments;
        this.ratingValue = ratingValue;
        this.rideID = rideID;
    }
}
