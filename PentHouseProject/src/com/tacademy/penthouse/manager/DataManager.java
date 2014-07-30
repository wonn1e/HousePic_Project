package com.tacademy.penthouse.manager;

import com.tacademy.penthouse.entity.ResultRooms;
import com.tacademy.penthouse.entity.RoomData;

public class DataManager {
	public RoomData getRoomData(ResultRooms rr, int r_num){
		for(int i = 0; i < rr.rooms.size(); i++){
			if(r_num == rr.rooms.get(i).room.room_num){
				return rr.rooms.get(i).room;
			}
		}
		
		return null;
		
	}

}
