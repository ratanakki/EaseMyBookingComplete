package com.easemybooking.dto;

public class PlaceDTO {
    private String id;
    private String placeName;
    private String img;
    private String description;
    private String location;
    private String map;

    // Constructors...

    public PlaceDTO() {
    }

    public PlaceDTO(String id,String placeName, String img, String description, String location, String map) {
        this.placeName = placeName;
        this.img = img;
        this.description = description;
        this.location = location;
        this.map = map;
        this.id=id;
    }

    // Getters and setters...

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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
}
