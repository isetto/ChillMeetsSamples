package com.example.ad.retrofittest.Model.Event;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventEdit extends AbstractEvent  {
	@SerializedName("event_kind_name")
	@Expose
	private String eventKindName;

	public String getEventKindName() {
		return eventKindName;
	}

	public EventEdit setEventKindName(String eventKindName) {
		this.eventKindName = eventKindName;
		return this;
	}

	public EventEdit() {
	}

	protected EventEdit(Parcel in) {
		eventKindName = in.readString();
	}


}