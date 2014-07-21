package com.tacademy.penthouse.room;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.penthouse.R;

public class MyRoomInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_room_info);
		
		ImageView room_img;
		TextView room_name;
		TextView room_update_time;
		TextView room_intro;
		
		TextView room_product_list;
		GridView room_item_gridview;
		
		room_img = (ImageView)findViewById(R.id.room_img);
		room_name = (TextView)findViewById(R.id.room_name);
		room_update_time = (TextView)findViewById(R.id.room_update_time); 
		room_intro = (TextView)findViewById(R.id.room_intro);
		room_product_list = (TextView)findViewById(R.id.room_product_list);
		room_item_gridview = (GridView)findViewById(R.id.room_item_gridview);
		
		
		
		
	}
}
