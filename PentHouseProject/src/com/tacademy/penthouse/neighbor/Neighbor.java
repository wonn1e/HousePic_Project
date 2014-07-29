package com.tacademy.penthouse.neighbor;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.UserData;

public class Neighbor extends FrameLayout {
	public Neighbor(Context context) {
		super(context);
		init();
	}
	
	UserData d;
	ImageView user_img, isFollowing;
	TextView user_name;

	ImageLoader loader;
	DisplayImageOptions options;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.neighbor_layout, this);
		user_img = (ImageView)findViewById(R.id.user_img);
		isFollowing = (ImageView)findViewById(R.id.isNeighbor);
		user_name = (TextView)findViewById(R.id.user_nickname);
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(100))
		.build();

	}
	public void setFollowingData(UserData data){
		d = data;
		//user_img.setImageResource(data.user_img);
		loader.displayImage(data.user_img_url, user_img,options);
		user_name.setText(data.user_nickname);
		
		//
		isFollowing.setImageResource(R.drawable.ic_launcher);
		///if()
	}

}
