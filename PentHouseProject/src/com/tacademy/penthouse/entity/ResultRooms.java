package com.tacademy.penthouse.entity;

import java.util.ArrayList;

public class ResultRooms {
	public ResultRooms(ArrayList<RoomInfoResult> a_rir) {
		this.rooms = a_rir;
	}

	public ResultRooms() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<RoomInfoResult> rooms = new ArrayList<RoomInfoResult>();

}
