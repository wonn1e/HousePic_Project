package com.tacademy.penthouse.house;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.RoomData;

public class RoomInHouseView extends FrameLayout {

	public RoomInHouseView(Context context) {
		super(context);
		init();
	}
	
	RoomData rData;
	ImageView room_img;
	TextView room_name;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.house_room_view, this);
		room_img =(ImageView)findViewById(R.id.room_img);
		room_name = (TextView)findViewById(R.id.room_name);
	}
	
	public void setHouseRoomData(RoomData d){
		rData = d;
//		room_img.setImageResource(d.room_img);
		room_img.setImageResource(R.drawable.ic_launcher); //
		room_name.setText(d.room_name);
	}
}
