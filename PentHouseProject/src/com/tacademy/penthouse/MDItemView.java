package com.tacademy.penthouse;

import com.tacademy.penthouse.entity.ItemData;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MDItemView extends FrameLayout {

	public MDItemView(Context context) {
		super(context);
		init();
	}
	
	ImageView item_like, item_img;
	TextView item_name, item_price;
	ItemData iData;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_md_item_view, this);
		item_like = (ImageView)findViewById(R.id.item_like);
		item_img = (ImageView)findViewById(R.id.item_img);
		item_name = (TextView)findViewById(R.id.item_name);
		item_price = (TextView)findViewById(R.id.item_price);
	}
	
	public void setItemData(ItemData d){
		iData = d;
		item_like.setImageResource(R.drawable.ic_launcher);
		item_img.setImageResource(R.drawable.penguins);
		item_name.setText(d.item_name);
		item_price.setText(d.price);
		
	}
}
