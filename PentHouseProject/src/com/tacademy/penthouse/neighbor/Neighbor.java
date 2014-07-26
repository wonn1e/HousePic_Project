package com.tacademy.penthouse.neighbor;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.UserData;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class Neighbor extends FrameLayout {
	public Neighbor(Context context) {
		super(context);
		init();
	}
	
	UserData d;
	ImageView user_img, isFollowing;
	TextView user_name;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.neighbor_layout, this);
		user_img = (ImageView)findViewById(R.id.user_img);
		isFollowing = (ImageView)findViewById(R.id.isNeighbor);
		user_name = (TextView)findViewById(R.id.user_nickname);
	}
	public void setFollowingData(UserData data){
		d = data;
		user_img.setImageResource(data.user_img);
		user_name.setText(data.user_nickname);
		
		//
		isFollowing.setImageResource(R.drawable.ic_launcher);
		///if()
	}

}
