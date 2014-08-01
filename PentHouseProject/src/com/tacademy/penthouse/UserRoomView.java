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
import com.tacademy.penthouse.entity.UserData;

public class UserRoomView extends FrameLayout {

	public UserRoomView(Context context) {
		super(context);
		init();
	}
	
	RoomData rData;
	UserData uData;
	TextView room_updateTime;
	ImageView user_room_img;
	ImageView user_img;
	ImageLoader loader;
	DisplayImageOptions options, userOpt;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_user_header_view, this);
		room_updateTime = (TextView)findViewById(R.id.room_updateTime);
		user_room_img = (ImageView)findViewById(R.id.user_room_img);
		user_img = (ImageView)findViewById(R.id.user_img);
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		userOpt = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(100))
		.build();
	}
	
	public void setData(RoomData data, 	UserData uD){
		rData = data;
		uData = uD;

		loader.displayImage(data.room_img_url, user_room_img, options);
		loader.displayImage(uD.user_img_url, user_img, userOpt);
		room_updateTime.setText(rData.room_date);
				
		
	}

}
