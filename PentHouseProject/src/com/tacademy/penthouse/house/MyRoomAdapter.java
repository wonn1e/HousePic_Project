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
		if(i != getCount()-1 && i!=0)
			items.remove(items.get(i));
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return items.size()+2;
	}
	
	@Override
	public int getViewTypeCount() {
		return 3;
	}

	@Override
	public int getItemViewType(int position) {
		if(position == 0) return -1;
		if(position == getCount()-1) return 0;
		return 1;
	}
	
	@Override
	public Object getItem(int position) {
		if(position == getCount()-1) return null;
		else if(position == 0) return null;
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(position == getCount()-1){
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
		v.setHouseRoomData(items.get(position));
		return v;
	}

}
