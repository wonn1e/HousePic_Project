package com.tacademy.penthouse.itemlike;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.item.SimillarInfoView;

public class RoomNameAdapter extends BaseAdapter {

	ArrayList<RoomData> rooms = new ArrayList<RoomData>();
	Context mContext;
	
	public void updateData(ItemData data, boolean isLike, int likeCnt) {
		data.item_like = isLike;
		data.likeCnt = likeCnt;
		notifyDataSetChanged();
	}
	
	public RoomNameAdapter(Context c){
		mContext = c;
	}
	
	public void add(RoomData d){
		rooms.add(d);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return rooms.size();
	}

	@Override
	public Object getItem(int position) {
		return rooms.get(position);
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
		v.setItemImageData(rooms.get(position));
		return v;
	}

}
