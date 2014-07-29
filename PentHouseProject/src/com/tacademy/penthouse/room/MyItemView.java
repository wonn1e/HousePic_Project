package com.tacademy.penthouse.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;

public class MyItemView extends FrameLayout{

	public MyItemView(Context context) {
		super(context);
		init();
	}
	
	public interface OnItemLikeClickListener{
		public void onLikeClick(View v, ItemData i);
	}
	public interface OnItemMoveClickListener{
		public void onMoveClick(View v, ItemData i);
	}
	
	OnItemLikeClickListener lListener;
	OnItemMoveClickListener mListener;
	public void setOnItemLikeClickListener(OnItemLikeClickListener l){
		lListener = l;
	}
	public void setOnItemMoveClickListener(OnItemMoveClickListener m){
		mListener = m;
	}
	

	ItemData iData;
	ImageView my_item_img;
	TextView my_item_name;
	ImageView my_item_like;
	TextView my_item_price;
	ImageView my_item_move;
	ImageLoader loader;
	DisplayImageOptions options;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.my_item_view, this);
		my_item_img = (ImageView)findViewById(R.id.my_item_img);
		my_item_name = (TextView)findViewById(R.id.my_item_name);
		my_item_like = (ImageView)findViewById(R.id.my_item_like);
		//item_price = (TextView)findViewById(R.id.item_price);	
		my_item_move = (ImageView)findViewById(R.id.my_item_move);
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
		my_item_move.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onMoveClick(MyItemView.this, iData);
				}
				Toast.makeText(getContext(), "Click Move",Toast.LENGTH_SHORT).show();
			}
		});
		
		
		
		my_item_like.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//좋아요 Count ++;
				
				if(lListener != null){
					lListener.onLikeClick(MyItemView.this, iData);
				}
				Toast.makeText(getContext(), "Cliked in MyItemView", Toast.LENGTH_SHORT ).show();
			}
		});
	}
	
	
	public void setItemData(ItemData data){
		iData = data;
		//첫번째 이미지를 나오게 한다.
	//	my_item_img.setImageResource(iData.item_img[0]);
		loader.displayImage(data.item_img_url[0], my_item_img,options);
		
		if(!data.item_like){
			my_item_like.setImageResource(R.drawable.tulips);
		}else{
			my_item_like.setImageResource(R.drawable.ic_launcher);
		}
	}
}