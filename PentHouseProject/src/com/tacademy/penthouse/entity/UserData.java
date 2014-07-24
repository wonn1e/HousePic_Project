package com.tacademy.penthouse.entity;


import android.os.Parcel;
import android.os.Parcelable;


public class UserData implements Parcelable{
	public int user_num;
	
	public String user_id;
	public String user_nickname;
	public	String user_password;
	public int following_cnt;
	public int follower_cnt;
	public int user_img;
	
	public UserData(){	}
	
	public UserData(int user_num, String user_id, String user_nickname, String user_password, int following_cnt, int follower_cnt, int user_img){
		this.user_num = user_num;
		this.user_id = user_id;
		this.user_nickname = user_nickname;
		this.user_password = user_password;
		this.follower_cnt = follower_cnt;
		this.following_cnt = following_cnt;
		this.user_img = user_img;
	}
	
	public UserData(Parcel p){
		user_num = p.readInt();
		user_id = p.readString();
		user_nickname = p.readString();
		user_password = p.readString();
		following_cnt = p.readInt();
		follower_cnt = p.readInt();
		user_img = p.readInt();
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(user_num);
		dest.writeString(user_id);
		dest.writeString(user_nickname);
		dest.writeString(user_password);
		dest.writeInt(following_cnt);
		dest.writeInt(follower_cnt);
		dest.writeInt(user_img);
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



/*
public class UserData implements Parcelable{
	public int user_num;
	
	public String user_id;
	public String user_nickname;
	public	String user_password;
	public int following_cnt;
	public int follower_cnt;
	public String user_img;
	
	public UserData(){	}
	
	public UserData(int user_num, String user_id, String user_nickname, String user_password, int following_cnt, int follower_cnt, String user_img){
		this.user_id = user_id;
		this.user_img = user_img;
		this.user_nickname = user_nickname;
		this.user_num = user_num;
		this.user_password = user_password;
		this.follower_cnt = follower_cnt;
		this.following_cnt = following_cnt;
	}
	
	public UserData(Parcel p){
		user_num = p.readInt();
		user_id = p.readString();
		user_nickname = p.readString();
		user_password = p.readString();
		following_cnt = p.readInt();
		follower_cnt = p.readInt();
		user_img = p.readString();
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(user_num);
		dest.writeString(user_id);
		dest.writeString(user_nickname);
		dest.writeString(user_password);
		dest.writeInt(following_cnt);
		dest.writeInt(follower_cnt);
		dest.writeString(user_img);
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
*/