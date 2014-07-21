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
	public boolean isPublic;
	
	public RoomData(){}
	
	public RoomData(Parcel p){
		house_num = p.readInt();
		room_num = p.readInt();
		room_name = p.readString();
		room_img = p.readString();
		room_info = p.readString();
		passedtime = new Date(p.readLong());
		isPublic = p.readByte() != 0;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(house_num);
		dest.writeInt(room_num);
		dest.writeString(room_name);
		dest.writeString(room_img);
		dest.writeString(room_info);
		dest.writeLong(passedtime.getTime());
		dest.writeByte((byte)(isPublic ? 1:0));
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
