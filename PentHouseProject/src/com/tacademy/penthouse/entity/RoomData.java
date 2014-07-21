package com.tacademy.penthouse.entity;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;


public class RoomData implements Parcelable{
		
	public int house_num;
	
	public int room_num;
	public String room_name;
	public String room_img;
	public String room_info;
	public Date passedtime;
	public String time;
	public boolean isPublic;
	
	public RoomData(){}
	
	public RoomData(Parcel p){
		house_num = p.readInt();
		room_num = p.readInt();
		room_name = p.readString();
		room_img = p.readString();
		room_info = p.readString();
		passedtime = new Date(p.readLong());
		passedtime.toString() = p.readString();
		time
		isPublic = p.readBooleanArray(val)
		p.
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeLong(passedtime.getTime());
	}
	
	public static Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator<UserData>() {

		@Override
		public UserData createFromParcel(Parcel source) {
			return new UserData(source);
		}

		@Override
		public UserData[] newArray(int size) {
			return new UserData[size];
		}
	};
}
