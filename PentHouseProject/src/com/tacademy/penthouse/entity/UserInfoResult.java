package com.tacademy.penthouse.entity;

import java.util.ArrayList;

public class UserInfoResult {
	public UserData user = new UserData();
	public ArrayList<RoomData> rooms = new ArrayList<RoomData>();

	public int success;
	public String result_msg;
}
