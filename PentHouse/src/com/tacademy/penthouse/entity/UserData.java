package com.tacademy.penthouse.entity;

public class UserData {
	int user_num;
	
	String user_id;
	String user_nickname;
	String user_password;
	UserData[] following;
	UserData[] follower;
	int user_img;
	HouseData house;
}
