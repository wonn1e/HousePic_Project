package com.tacademy.penthouse.house;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;

public class RankItemView extends FrameLayout {

	public RankItemView(Context context) {
		super(context);
		init();
	}
	
	ItemData iData;
	ImageView item_img;
	ImageView item_rank;
	TextView item_name;
	TextView item_price;
	TextView item_like_num;
	ImageView item_like;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.rank_item_view, this);
		item_img = (ImageView)findViewById(R.id.item_img);
		item_rank = (ImageView)findViewById(R.id.item_rank);
		item_name = (TextView)findViewById(R.id.item_name);
		item_price = (TextView)findViewById(R.id.item_price);
		item_like_num = (TextView)findViewById(R.id.item_like_num);
		item_like = (ImageView)findViewById(R.id.item_like);
	}

	public void setRankItemData(ItemData data){
		iData = data;
		item_img.setImageResource(resId);
		item_rank.setImageResource(R.drawable.ic_launcher);
		item_name.setText(data.item_name);
		item_price.setText(data.price);
		item_like_num.setText(data.likeCnt);
		item_like.setImageResource(resId);
	}
}
