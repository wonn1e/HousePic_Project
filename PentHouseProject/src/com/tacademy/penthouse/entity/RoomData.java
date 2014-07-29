package com.tacademy.penthouse.entity;

import java.util.ArrayList;
import java.util.Date;


import android.os.Parcel;
import android.os.Parcelable;


public class RoomData implements Parcelable{
		
	public int user_num;
	
	public int room_num;
	public String room_name;
	public String room_img_url;
	public String room_info;
	//public Date passedtime;
	public boolean isPublic;
	public ArrayList<ItemData> items = new ArrayList<ItemData>();
	public RoomData(){}
	
	public RoomData(int u_n, int r_num, String r_name, String r_img, String r_info, boolean ispublic){
		this.user_num = u_n;
		this.room_num = r_num;
		this.room_name = r_name;
		this.room_img_url = r_img;
		this.room_info = r_info;
		this.isPublic = ispublic;
	}
	
	public RoomData(Parcel p){
		user_num = p.readInt();
		room_num = p.readInt();
		room_name = p.readString();
		room_img_url = p.readString();
		room_info = p.readString();
	//	passedtime = new Date(p.readLong());
		isPublic = p.readByte() != 0;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(user_num);
		dest.writeInt(room_num);
		dest.writeString(room_name);
		dest.writeString(room_img_url);
		dest.writeString(room_info);
	//	dest.writeLong(passedtime.getTime());
		dest.writeByte((byte)(isPublic ? 1:0));
	}
	
	public static Parcelable.Creator<RoomData> CREATOR = new Parcelable.Creator<RoomData>() {

		@Override
		public RoomData createFromParcel(Parcel source) {
			return new RoomData(source);
		}

		@Override
		public RoomData[] newArray(int size) {
			return new RoomData[size];
		}
	};
}
