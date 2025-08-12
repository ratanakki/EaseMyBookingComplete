package com.easemybooking.bookingdetails.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String emailID;
    private String placeName;
    private String name;
    private int numberOfAdults;
    private int numberOfChildren;
    private LocalDate dateOfBooking;
    private double cost;
    private String ownerEmailId;  // New field

    // Constructors, Getters and Setters
    public Booking() {}

    public Booking(String id,String emailID, String name, int numberOfAdults, int numberOfChildren, LocalDate dateOfBooking,String ownerEmailId) {
        this.id=id;
    	this.emailID = emailID;
        this.name = name;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.dateOfBooking = dateOfBooking;
        this.ownerEmailId = ownerEmailId;  // Set the ownerEmailId
        this.cost = calculateCost();
    }

    // Calculate cost based on the number of adults and children
    public double calculateCost() {
        return (numberOfAdults * 60) + (numberOfChildren * 20);
    }

    // Getters and Setters
    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getOwnerEmailId() {
        return ownerEmailId;
    }

    public void setOwnerEmailId(String ownerEmailId) {
        this.ownerEmailId = ownerEmailId;
    }
}
