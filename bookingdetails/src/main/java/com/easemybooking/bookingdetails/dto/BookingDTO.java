package com.easemybooking.bookingdetails.dto;

public class BookingDTO {

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
    private String dateOfBooking;
    private String ownerEmailId; // New field

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

    public String getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(String dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public String getOwnerEmailId() {
        return ownerEmailId;
    }

    public void setOwnerEmailId(String ownerEmailId) {
        this.ownerEmailId = ownerEmailId;
    }

    // Constructors
    public BookingDTO(String id,String emailID, String name, int numberOfAdults, int numberOfChildren, String dateOfBooking, String ownerEmailId) {
        this.id=id;
    	this.emailID = emailID;
        this.name = name;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.dateOfBooking = dateOfBooking;
        this.ownerEmailId = ownerEmailId;
    }
}
