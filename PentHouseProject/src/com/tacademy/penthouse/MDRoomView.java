package com.tacademy.penthouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.penthouse.entity.RoomData;

public class MDRoomView extends FrameLayout {

	public MDRoomView(Context context) {
		super(context);
		init();
	}
	
	RoomData rData;
	TextView md_room_name;
	ImageView md_room_img;
	ImageLoader loader;
	
	DisplayImageOptions options;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_md_header_view, this);
		md_room_name = (TextView)findViewById(R.id.room_info);
		md_room_img = (ImageView)findViewById(R.id.room_img);
		
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
	//	.displayer(new RoundedBitmapDisplayer(100))
		.build();
	}
	
	public void setData(RoomData data){
		rData = data;
		//md_room_img.setImageResource(data.room_img);
		loader.displayImage(data.room_img_url,md_room_img,options);

		md_room_name.setText(data.room_name);

		
		
		
		
	}

}
