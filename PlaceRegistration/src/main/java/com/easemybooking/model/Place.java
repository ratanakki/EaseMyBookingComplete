package com.easemybooking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "easemybooking")
public class Place {

    @Id
    private String id;
    private String ownerEmailId;
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	private String placeName;
    private byte[] img;
    private String description;
    private String location;
    private String map;

    public Place( String ownerEmailId, String placeName, byte[] img, String description, String location, String map) {
      
    	this.ownerEmailId = ownerEmailId;
        this.placeName = placeName;
        this.img = img;
        this.description = description;
        this.location = location;
        this.map = map;
    }

    public Place() {
    }

    public String getOwnerEmailId() {
        return ownerEmailId;
    }

    public void setOwnerEmailId(String ownerEmailId) {
        this.ownerEmailId = ownerEmailId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Place{" +
                "ownerEmailId='" + ownerEmailId + '\'' +
                ", placeName='" + placeName + '\'' +
                ", img=" + img +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", map='" + map + '\'' +
                '}';
    }
}
