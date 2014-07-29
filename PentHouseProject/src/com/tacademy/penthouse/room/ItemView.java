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

public class ItemView extends FrameLayout{

	public ItemView(Context context) {
		super(context);
		init();
	}
	
	public interface OnItemLikeClickListener{
		public void onLikeClick(View v, ItemData i);
	}
	
	OnItemLikeClickListener mListener;
	public void setOnItemLikeClickListener(OnItemLikeClickListener l){
		mListener = l;
	}

	ItemData iData;
	ImageView item_img;
	TextView item_name;
	ImageView item_like;
	TextView item_price;

	ImageLoader loader;
	DisplayImageOptions options;
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.item_view, this);
		item_img = (ImageView)findViewById(R.id.item_img);
		item_name = (TextView)findViewById(R.id.item_name);
		item_like = (ImageView)findViewById(R.id.item_like);
		//item_price = (TextView)findViewById(R.id.item_price);	
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
		
		item_like.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//여기서 처리하나요~~~~~?
				//좋아요 Count ++;
				
				if(mListener != null){
					mListener.onLikeClick(ItemView.this, iData);
				}
				Toast.makeText(getContext(), "Cliked in ItemView", Toast.LENGTH_SHORT ).show();
			}
		});
	}
	
	
	public void setItemData(ItemData data){
		iData = data;
		//첫번째 이미지를 나오게 한다.
		//item_img.setImageResource(iData.item_img[0]);
		loader.displayImage(data.item_img_url[0], item_img,options);
		if(!data.item_like){
			item_like.setImageResource(R.drawable.tulips);
		}else{
			item_like.setImageResource(R.drawable.ic_launcher);
		}
	}
}