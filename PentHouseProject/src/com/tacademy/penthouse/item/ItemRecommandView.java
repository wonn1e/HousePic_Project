package com.tacademy.penthouse.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;


public class ItemRecommandView extends FrameLayout{

	public ItemRecommandView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}
	Integer iResId;
	ImageView item_img;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.item_img_view, this);
		item_img = (ImageView)findViewById(R.id.item_img);
		
	}
	
	public void setItemRecommandData(Integer resId){
		iResId = resId;
		item_img.setImageResource(resId);
	}

}
