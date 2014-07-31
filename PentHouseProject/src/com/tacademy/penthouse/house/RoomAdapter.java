package com.tacademy.penthouse.house;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserRoomsData;

public class RoomAdapter extends BaseAdapter{
	UserRoomsData urData;	
	ArrayList<RoomData> rooms = new ArrayList<RoomData>();
	Context mContext;
	
	public RoomAdapter(Context c){ 
		mContext = c;
	}
	/*
	public void add(RoomData r){
		rooms.add(r);
		notifyDataSetChanged();
	}*/
	
	public void put(UserRoomsData rD){
		urData = rD;
		for(int i= 0; i<rD.rooms.size(); i++){
			rooms.add(rD.rooms.get(i));
		}
		notifyDataSetChanged();
	}

	public void remove(int i){
		rooms.remove(rooms.get(i));
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return rooms.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return rooms.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RoomInHouseView v;
		if(convertView == null){
			v = new RoomInHouseView(mContext);
		}else{
			v = (RoomInHouseView)convertView;
		}
		v.setHouseRoomData(rooms.get(position));
		return v;
	}

}
