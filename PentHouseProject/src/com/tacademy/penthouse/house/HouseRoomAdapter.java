package com.tacademy.penthouse.house;

import java.util.ArrayList;

import com.tacademy.penthouse.entity.RoomData;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HouseRoomAdapter extends BaseAdapter {
	ArrayList<RoomData> list = new ArrayList<RoomData>();
	Context mContext;
	
	public HouseRoomAdapter(Context c){
		mContext = c;
	}
	
	public void add(RoomData d){
		list.add(d);
		notifyDataSetChanged();
	}

	public void delete(RoomData d){
		list.remove(d);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//////////////////////////////////
		return null;
	}

}
