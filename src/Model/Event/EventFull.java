package com.example.ad.retrofittest.Model.Event;

import com.example.ad.retrofittest.Model.EventGuests;
import com.example.ad.retrofittest.Model.UserNewModel.UserMin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventFull extends EventMin {
	@SerializedName("guests")
	@Expose
	private EventGuests guests;

	@SerializedName("mods")
	@Expose
	private List<UserMin> mods;

	@SerializedName("my_status")
	@Expose
	private String myStatus;

	public String getMyStatus() {
		return myStatus;
	}

	public void setMyStatus(String myStatus) {
		this.myStatus = myStatus;
	}

	@SerializedName("creator")

	@Expose
	private UserMin creator;

	public EventGuests getGuests() {
		return guests;
	}

	public EventFull setGuests(EventGuests guests) {
		this.guests = guests;
		return this;
	}

	public List<UserMin> getMods() {
		return mods;
	}

	public EventFull setMods(List<UserMin> mods) {
		this.mods = mods;
		return this;
	}

	public UserMin getCreator() {
		return creator;
	}

	public EventFull setCreator(UserMin creator) {
		this.creator = creator;
		return this;
	}
}
