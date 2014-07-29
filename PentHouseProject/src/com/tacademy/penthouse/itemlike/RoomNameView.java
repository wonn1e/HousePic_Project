package com.tacademy.penthouse.itemlike;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.RoomData;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class RoomNameView extends FrameLayout{

	public RoomNameView(Context context) {
		super(context);
		init();
	}
	
	RoomData rData;
	TextView room_name;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.room_name, this);
		room_name = (TextView)findViewById(R.id.room_name);
	}
	
	public void setItemImageData(RoomData r){
		rData = r;
		room_name.setText(r.room_name);
		//해당 방 색깔로
	//	setBackgroundColor(color);
	}

}
