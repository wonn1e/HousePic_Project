package com.tacademy.penthouse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.item.ItemInfoActivity;

public class MDItemView extends FrameLayout{

	public MDItemView(Context context) {
		super(context);
		init();
	}
	
	public interface OnItemDataClickListener {
		public void onItemClick(View v,ItemData data);
	}
	
	public interface OnItemDataLikeClickListener{
		public void onItemDataLikeClick(View v, ItemData data);
	}
	
	OnItemDataClickListener mListener;
	public void setOnItemDataClickListener(OnItemDataClickListener listener) {
		mListener = listener;
	}
	
	OnItemDataLikeClickListener lListener;
	public void setOnItemDataLikeClickListener(OnItemDataLikeClickListener listener){
		lListener = listener;
	}
	
	
	ItemData rData;
	ItemData lData;
	ImageView item_left_img, item_left_like;
	ImageView item_right_img, item_right_like;
	TextView item_left_name;
	TextView item_right_name;
	TextView item_left_price;
	TextView item_right_price;
	TextView md_cnt;
	TextView md_more;
	ImageLoader loader;
	DisplayImageOptions options;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_md_double_item_view, this);
		
		item_left_img = (ImageView)findViewById(R.id.item_left_img);
		item_left_like = (ImageView)findViewById(R.id.item_left_like);
		item_right_img = (ImageView)findViewById(R.id.item_right_img);
		item_right_like = (ImageView)findViewById(R.id.item_right_like);
		item_left_name = (TextView)findViewById(R.id.item_left_name);
		item_right_name = (TextView)findViewById(R.id.item_right_name);
		item_left_price = (TextView)findViewById(R.id.item_left_price);
		item_right_price = (TextView)findViewById(R.id.item_right_price);
		md_cnt = (TextView)findViewById(R.id.cnt);
		md_more = (TextView)findViewById(R.id.more);
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

		
		item_left_img.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "LeftItemClick", Toast.LENGTH_SHORT).show();
				if(mListener != null){
					mListener.onItemClick(MDItemView.this, lData);
					
				}
//				Intent i = new Intent(getContext(), ItemInfoActivity.class  );
//				i.putExtra("iData", lData);
//				
//				startActivity(i);
			}
		});
		item_right_img.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "rightItemClick", Toast.LENGTH_SHORT).show();
				if(mListener != null){
					mListener.onItemClick(MDItemView.this, rData);
				}
			}
		});
		item_left_like.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(lListener != null){
					lListener.onItemDataLikeClick(MDItemView.this, lData);
				}
			}
		});
		item_right_like.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(lListener != null){
					lListener.onItemDataLikeClick(MDItemView.this, rData);
				}
			}
		});
	}
	
	public void setData(ItemData leftData,ItemData rightData){
		lData = leftData;
		rData = rightData;		
		
		//item_left_img.setImageResource(leftData.item_img[0]);
		loader.displayImage(leftData.item_img_url[0], item_left_img, options);
		item_left_name.setText(leftData.item_name);
		item_left_price.setText(leftData.price);
		if(leftData.item_like){
			item_left_like.setImageResource(R.drawable.ic_launcher);
		}else{
			item_left_like.setImageResource(R.drawable.tulips);
		}
		
		if(rightData != null ){
			//item_right_img.setImageResource(rightData.item_img[0]);
			loader.displayImage(rightData.item_img_url[0], item_right_img, options);
			item_right_name.setText(rightData.item_name);
			item_right_price.setText(rightData.price);
			if(rightData.item_like){
				item_right_like.setImageResource(R.drawable.ic_launcher);
			}else{
				item_right_like.setImageResource(R.drawable.tulips);
			}
			
		}
	}
	
	
}
