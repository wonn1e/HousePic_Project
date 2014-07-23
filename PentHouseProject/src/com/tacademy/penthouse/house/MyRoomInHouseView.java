package com.tacademy.penthouse.house;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.RoomData;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MyRoomInHouseView extends FrameLayout {

	public MyRoomInHouseView(Context context) {
		super(context);
		init();
	}
	
	RoomData rData;
	ImageView room_img, room_edit;
	TextView room_name;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.house_myroom_view, this);
		room_img =(ImageView)findViewById(R.id.room_img);
		room_name = (TextView)findViewById(R.id.room_name);
		room_edit = (ImageView)findViewById(R.id.edit_btn);
	}
	
	public void setHouseRoomData(RoomData d){
		rData = d;
//		room_img.setImageResource(d.room_img);
		room_img.setImageResource(R.drawable.tulips); //
		room_name.setText(d.room_name);
		room_edit.setImageResource(R.drawable.abc_ab_solid_light_holo);
	}

}
