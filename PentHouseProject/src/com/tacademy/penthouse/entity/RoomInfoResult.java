package com.tacademy.penthouse.entity;

import java.util.ArrayList;
public class RoomInfoResult {
	public RoomData room = new RoomData();
	public ArrayList<ItemData> items = new ArrayList<ItemData>();

	public String result;
	public String result_msg;
	
	public RoomInfoResult(RoomData room, ArrayList<ItemData> items,
			String result, String result_msg) {
		super();
		this.room = room;
		this.items = items;
		this.result = result;
		this.result_msg = result_msg;
	}
	
	
}
