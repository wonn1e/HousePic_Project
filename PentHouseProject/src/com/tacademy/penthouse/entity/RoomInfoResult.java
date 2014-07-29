package com.tacademy.penthouse.entity;

import java.util.ArrayList;

public class RoomInfoResult {
	public RoomData room = new RoomData();
	public ArrayList<ItemData> similarItems = new ArrayList<ItemData>();

	public String result;
	public String result_msg;
}
