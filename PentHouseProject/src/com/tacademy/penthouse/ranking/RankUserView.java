package com.tacademy.penthouse.ranking;


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



public class RankUserView extends FrameLayout {

	public RankUserView(Context context) {
		super(context);
		init();
	}

	UserData uData;
	ImageView user_img;
	ImageView show_rank;
	TextView user_nickname;
	TextView follower_num;
	ImageView follower_btn;
	ImageLoader loader;
	DisplayImageOptions options;

	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.rank_user_view, this);
		user_img = (ImageView)findViewById(R.id.userImgRank);
		show_rank = (ImageView)findViewById(R.id.showUserRank);
		user_nickname = (TextView)findViewById(R.id.userNicknameInRank);
		follower_num = (TextView)findViewById(R.id.userFollowerNum);
		follower_btn = (ImageView)findViewById(R.id.userFollowingBtn);
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

	public void setRankUserData(UserData data, int position){
		uData = data;
		if(position == 0 || position == 1 || position == 2){
			show_rank.setVisibility(VISIBLE);
			show_rank.setImageResource(R.drawable.ic_launcher);
		}
		else{
			show_rank.setVisibility(GONE);
		}
		//user_img.setImageResource(data.user_img);
		loader.displayImage(data.user_img_url, user_img, options);

		user_nickname.setText(data.user_nickname);
		follower_num.setText(data.follower_cnt+"");
		follower_btn.setImageResource(R.drawable.ic_launcher);
	}
}
