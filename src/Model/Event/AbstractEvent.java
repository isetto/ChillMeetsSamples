package com.example.ad.retrofittest.Model.Event;

import com.example.ad.retrofittest.Model.Photo.PhotoFull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public abstract class AbstractEvent {
	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("description")
	@Expose
	private String description;

	@SerializedName("address")
	@Expose
	private String address;

	@SerializedName("start_date")
	@Expose
	private Date startDate;

	@SerializedName("end_date")
	@Expose
	private Date endDate;

	@SerializedName("lat")
	@Expose
	private Double lat;

	@SerializedName("lng")
	@Expose
	private Double lng;

	@SerializedName("main_photo")
	@Expose
	private PhotoFull mainPhoto;

	@SerializedName("event_type")
	@Expose
	private String eventAccess;

	public String getName() {
		return name;
	}

	public AbstractEvent setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public AbstractEvent setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public AbstractEvent setAddress(String address) {
		this.address = address;
		return this;
	}

	public Date getStartDate() {
		return startDate;
	}

	public AbstractEvent setStartDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}

	public Date getEndDate() {
		return endDate;
	}

	public AbstractEvent setEndDate(Date endDate) {
		this.endDate = endDate;
		return this;
	}

	public Double getLat() {
		return lat;
	}

	public AbstractEvent setLat(Double lat) {
		this.lat = lat;
		return this;
	}

	public Double getLng() {
		return lng;
	}

	public AbstractEvent setLng(Double lng) {
		this.lng = lng;
		return this;
	}

	public PhotoFull getMainPhoto() {
		return mainPhoto;
	}

	public AbstractEvent setMainPhoto(PhotoFull mainPhoto) {
		this.mainPhoto = mainPhoto;
		return this;
	}

	public String getEventAccess() {
		return eventAccess;
	}

	public AbstractEvent setEventAccess(String eventAccess) {
		this.eventAccess = eventAccess;
		return this;
	}
}
