package com.tacademy.penthouse.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;

public class SimillarInfoView extends FrameLayout{

	public SimillarInfoView(Context context) {
		super(context);
		init();
	}
	ItemData iData;
	ImageView item_img;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.item_img_view, this);
		item_img = (ImageView)findViewById(R.id.item_img);
		
	}

	public void setItemImageData(ItemData data){
		iData = data;
		for(int i = 0; i < data.item_img.length ; i++){
			item_img.setImageResource(data.item_img[i]);
		}
		
	}
}
