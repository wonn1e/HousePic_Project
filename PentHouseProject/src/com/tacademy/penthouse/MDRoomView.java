package com.tacademy.penthouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.tacademy.penthouse.entity.RoomData;

public class MDRoomView extends FrameLayout {

	public MDRoomView(Context context) {
		super(context);
		init();
	}
	
	RoomData rData;
	ImageView md_room_img;
	ListView md_item_list;
	
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_md_room_view, this);
		md_room_img = (ImageView)findViewById(R.id.md_room_img);
		md_item_list = (ListView)findViewById(R.id.md_item_list);
	}
	
	public void setData(RoomData data){
		rData = data;
		md_room_img.setImageResource(data.room_img);
		
		
		
	}

}
