package com.tacademy.penthouse.itemlike;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.item.SimillarInfoView;

public class RoomNameAdapter extends BaseAdapter {

	ArrayList<RoomData> items = new ArrayList<RoomData>();
	Context mContext;
	
	public RoomNameAdapter(Context c){
		mContext = c;
	}
	
	public void add(RoomData d){
		items.add(d);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return items.size();
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
		RoomNameView v;
		if(convertView == null){
			v = new RoomNameView(mContext);	
		}else{
			v = (RoomNameView)convertView;
		}
		v.setItemImageData(items.get(position));
		return v;
	}

}
