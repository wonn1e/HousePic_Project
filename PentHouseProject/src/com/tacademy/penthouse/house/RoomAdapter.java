package com.tacademy.penthouse.house;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.tacademy.penthouse.entity.RoomData;

public class RoomAdapter extends BaseAdapter{
	
	ArrayList<RoomData> items = new ArrayList<RoomData>();
	Context mContext;
	
	public RoomAdapter(Context c){ 
		mContext = c;
	}
	
	public void add(RoomData r){
		items.add(r);
		notifyDataSetChanged();
	}

	public void remove(int i){
		items.remove(items.get(i));
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
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
		v.setHouseRoomData(items.get(position));
		return v;
	}

}
