package com.masstudios.foreignrap.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The JSON returns a List of these items
 *
 * @author mofa
 * @since 2017-08-20
 */

public class Video implements Parcelable {

	private String country;

	private String title;

	private String id;

	private String youtube;

	private String tidal;

	private String apple;

	private String spotify;

	public Video() {
	}

	protected Video(Parcel in) {
		country = in.readString();
		title = in.readString();
		id = in.readString();
		youtube = in.readString();
		tidal = in.readString();
		apple = in.readString();
		spotify = in.readString();
	}

	public static final Creator<Video> CREATOR = new Creator<Video>() {
		@Override
		public Video createFromParcel(Parcel in) {
			return new Video(in);
		}

		@Override
		public Video[] newArray(int size) {
			return new Video[size];
		}
	};

	public String getCountry() {
		return country;
	}

	public Video setCountry(String country) {
		this.country = country;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Video setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getId() {
		return id;
	}

	public Video setId(String id) {
		this.id = id;
		return this;
	}

	public String getYoutube() {
		return youtube;
	}

	public Video setYoutube(String youtube) {
		this.youtube = youtube;
		return this;
	}

	public String getTidal() {
		return tidal;
	}

	public Video setTidal(String tidal) {
		this.tidal = tidal;
		return this;
	}

	public String getApple() {
		return apple;
	}

	public Video setApple(String apple) {
		this.apple = apple;
		return this;
	}

	public String getSpotify() {
		return spotify;
	}

	public Video setSpotify(String spotify) {
		this.spotify = spotify;
		return this;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(country);
		parcel.writeString(title);
		parcel.writeString(id);
		parcel.writeString(youtube);
		parcel.writeString(tidal);
		parcel.writeString(apple);
		parcel.writeString(spotify);
	}
}
