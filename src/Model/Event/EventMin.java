package com.example.ad.retrofittest.Model.Event;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ad.retrofittest.Model.EventKind;
import com.example.ad.retrofittest.Model.UserNewModel.UserMin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventMin extends AbstractEvent implements Parcelable {

	public EventMin() {
	}

	@SerializedName("event_id")
	@Expose
	private int id;

	@SerializedName("id")
	@Expose
	private int idTemporary; //TODO: zmienic to

	public int getIdTemporary() {
		return idTemporary;
	}

	@SerializedName("event_kind")
	@Expose()
	private EventKind eventKind;

	public EventMin(int id) {
		this.id = id;
	}

	@SerializedName("creator_min")
	@Expose
	private UserMin creatorMin;

	public int getId() {
		return id;
	}

	public EventMin setId(int id) {
		this.id = id;
		return this;
	}

	public EventKind getEventKind() {
		return eventKind;
	}

	public EventMin setEventKind(EventKind eventKind) {
		this.eventKind = eventKind;
		return this;
	}

	public UserMin getCreatorMin() {
		return creatorMin;
	}

	public EventMin setCreatorMin(UserMin creatorMin) {
		this.creatorMin = creatorMin;
		return this;
	}

	public String getEventKindName() {return  getEventKind().getName();}





	protected EventMin(Parcel in) {
		id = in.readInt();
		eventKind = (EventKind) in.readValue(EventKind.class.getClassLoader());
		creatorMin = (UserMin) in.readValue(UserMin.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeValue(eventKind);
		dest.writeValue(creatorMin);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<EventMin> CREATOR = new Parcelable.Creator<EventMin>() {
		@Override
		public EventMin createFromParcel(Parcel in) {
			return new EventMin(in);
		}

		@Override
		public EventMin[] newArray(int size) {
			return new EventMin[size];
		}
	};
}

