package com.tacademy.penthouse;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.UserData;

public class MDRoomAdapter extends BaseAdapter {
	ArrayList<UserData> items = new ArrayList<UserData>();
	Context mContext;
	
	public MDRoomAdapter(Context context){
		mContext = context;
	}
	public void add(UserData data){
		items.add(data);
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
		
		
		return null;
	}

}
