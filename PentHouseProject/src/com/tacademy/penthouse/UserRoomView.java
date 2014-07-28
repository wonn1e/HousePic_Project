package com.tacademy.penthouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.penthouse.entity.RoomData;

public class UserRoomView extends FrameLayout {

	public UserRoomView(Context context) {
		super(context);
		init();
	}
	
	RoomData rData;
	TextView user_room_info;
	ImageView user_room_img;
	ImageView user_img;
	
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_user_header_view, this);
		user_room_info = (TextView)findViewById(R.id.user_room_info);
		user_room_img = (ImageView)findViewById(R.id.user_room_img);
		user_img = (ImageView)findViewById(R.id.user_img);
	}
	
	public void setData(RoomData data,int userResId){
		rData = data;
		user_room_img.setImageResource(rData.room_img);
		user_room_info.setText(rData.room_info);
		user_img.setImageResource(userResId);
		
		
		
		
		
	}

}
