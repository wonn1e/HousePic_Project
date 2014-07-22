package com.tacademy.penthouse.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class HouseData implements Parcelable{
	
	public int user_num;
	
	public int house_num;
	public String house_name;
	public String house_intro;
	public String house_img;
	
	public HouseData(){}
	
	public HouseData(int u_n, int h_num, String h_name, String h_intro, String h_img){
		this.user_num = u_n;
		this.house_num = h_num;
		this.house_name = h_name;
		this.house_intro = h_intro;
		this.house_img = h_img;
	}
	
	public HouseData(Parcel p){
		user_num = p.readInt();
		house_num = p.readInt();
		house_name = p.readString();
		house_intro = p.readString();
		house_img = p.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(user_num);
		dest.writeInt(house_num);
		dest.writeString(house_name);
		dest.writeString(house_intro);
		dest.writeString(house_img);
	}
	
	public static Parcelable.Creator<HouseData> CREATOR = new Parcelable.Creator<HouseData>() {

		@Override
		public HouseData createFromParcel(Parcel source) {
			return new HouseData(source);
		}

		@Override
		public HouseData[] newArray(int size) {
		
			return new HouseData[size];
		}
	};
}
