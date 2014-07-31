package com.tacademy.penthouse.house;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
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
	ImageLoader loader;
	DisplayImageOptions options;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.house_room_view, this);
		room_img =(ImageView)findViewById(R.id.room_img);
		room_name = (TextView)findViewById(R.id.room_name);
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		//.displayer(new RoundedBitmapDisplayer(100))
		.build();
	}
	
	public void setHouseRoomData(RoomData d){
		rData = d;
		loader.displayImage(d.room_img_url ,room_img , options);
		room_name.setText(d.room_name);
	}
}
