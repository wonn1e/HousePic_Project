package com.tacademy.penthouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.tacademy.penthouse.entity.RoomData;

public class MDRoomView extends FrameLayout {

	public MDRoomView(Context context) {
		super(context);
		init();
	}
	
	RoomData rData;
	TextView md_room_info;
	ImageView md_room_img;
	
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_md_header_view, this);
		md_room_info = (TextView)findViewById(R.id.room_info);
		md_room_img = (ImageView)findViewById(R.id.room_img);
	}
	
	public void setData(RoomData data){
		rData = data;
		md_room_img.setImageResource(data.room_img);
		md_room_info.setText(data.room_info);
		
		
		
		
		
	}

}
