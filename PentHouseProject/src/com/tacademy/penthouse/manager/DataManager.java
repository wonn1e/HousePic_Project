package com.tacademy.penthouse.manager;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.RoomsData;

public class DataManager {
	public RoomData getRoomData(RoomsData rr, int r_num){
		for(int i = 0; i < rr.rooms.size(); i++){
			if(r_num == rr.rooms.get(i).room.room_num){
				return rr.rooms.get(i).room;
			}
		}
		
		return null;
		
	}

	public ItemData getRIItemData(RoomsData resultRooms, int roomNum, int itemPos){
		if(itemPos != 5)
			return resultRooms.rooms.get(roomNum).items.get(itemPos);
		return null;
	}	
}
