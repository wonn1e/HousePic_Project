package com.tacademy.penthouse.house;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.neighbor.NeighborListActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class HouseView extends FrameLayout{

	public HouseView(Context context) {
		super(context);
		init();
	}
	UserData uData;
	TextView user_nickname, house_name, house_intro, house_room_list;
	ImageView user_img, house_img, edit_btn;
	Button following_btn, follower_btn;
	boolean isClicked;
	ImageLoader loader;
	DisplayImageOptions userOptions, houseOptions;
//Listener 만들기
	//	
//	private void setClicked(boolean clicked){
//		if(isClicked != clicked){
//			isClicked = clicked;
//			drawIsClick();
//		}
//		else{
//			isClicked = !clicked;
//			drawIsClick();
//		}
//	}
//	//click일때 아닐때 그림바꾸기
//	private void drawIsClick(){
//		if(isClicked){
//			edit_btn.setImageResource(android.R.drawable.checkbox_on_background);
//		}else{
//			edit_btn.setImageResource(android.R.drawable.checkbox_off_background);
//		}
//	}

	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.header_view_house_layout, this);
		user_nickname = (TextView)findViewById(R.id.userNicknameHouse);
		house_name = (TextView)findViewById(R.id.houseNameHouse);
		house_intro = (TextView)findViewById(R.id.houseInfoHouse);
		user_img = (ImageView)findViewById(R.id.userImgHouse);
		house_img = (ImageView)findViewById(R.id.houseImgHouse);
		edit_btn = (ImageView)findViewById(R.id.editHouseBtn);
		house_room_list = (TextView)findViewById(R.id.house_room_list);
		following_btn = (Button)findViewById(R.id.followingBtnHouse);
		follower_btn = (Button)findViewById(R.id.followerBtnHouse);
		
		loader = ImageLoader.getInstance();
		houseOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		userOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(100))
		.build();
		
		edit_btn.setVisibility(View.GONE);
		following_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(), NeighborListActivity.class);
				i.putExtra(NeighborListActivity.PARAM_CURRENT_TAB, 0);//uData);
			//	startActivity(i);
			}
		});

		
		follower_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(), NeighborListActivity.class);
				i.putExtra(NeighborListActivity.PARAM_CURRENT_TAB, 1);//uData);
			//	startActivity(i);
			}
		});
	}
	
	public void setData(UserData data){
		uData = data;
		user_nickname.setText(uData.user_nickname);
		house_name.setText(uData.house_name);
		house_intro.setText(uData.house_intro);
		//house_room_list.setText(uData.user_nickname + "의 방 목록");
		loader.displayImage(uData.user_img_url, user_img, userOptions);
		loader.displayImage(uData.house_img_url , house_img, houseOptions);

	}
}
