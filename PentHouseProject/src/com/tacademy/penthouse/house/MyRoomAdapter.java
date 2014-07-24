package com.tacademy.penthouse.house;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.RoomData;

public class MyRoomAdapter extends BaseAdapter {
	ArrayList<RoomData> items = new ArrayList<RoomData>();
	Context mContext;
	
	public MyRoomAdapter(Context c){ 
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
		return items.size()+1;
	}
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		if(position == 0) return 0;
		return 1;
	}
	
	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(position == 0){
			ImageView im = new ImageView(mContext);
			im.setImageResource(R.drawable.penguins);
			im.setLayoutParams(new AbsListView.LayoutParams(160, 320));
			return im;
		}
		
		RoomInHouseView v;
		if(convertView == null){
			v = new RoomInHouseView(mContext);
		}else{
			v = (RoomInHouseView)convertView;
		}
		v.setHouseRoomData(items.get(position-1));
		return v;
	}

}
