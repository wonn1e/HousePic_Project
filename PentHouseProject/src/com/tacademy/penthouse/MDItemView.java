package com.tacademy.penthouse;

import com.tacademy.penthouse.entity.ItemData;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MDItemView extends FrameLayout{

	public MDItemView(Context context) {
		super(context);
		init();
	}
//	ItemData riData;
//	ItemData liData;
	//...
	ImageView item_left_img;
	ImageView item_right_img;
	TextView item_left_name;
	TextView item_right_name;
	TextView item_left_price;
	TextView item_right_price;
	
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_md_double_item_view, this);
		
		item_left_img = (ImageView)findViewById(R.id.item_left_img);
		item_right_img = (ImageView)findViewById(R.id.item_right_img);
		item_left_name = (TextView)findViewById(R.id.item_left_name);
		item_right_name = (TextView)findViewById(R.id.item_right_name);
		item_left_price = (TextView)findViewById(R.id.item_left_price);
		item_right_price = (TextView)findViewById(R.id.item_right_price);
		
	}
	
	public void setData(ItemData leftData,ItemData rightData){
		//iData = data;
		
		item_left_img.setImageResource(leftData.item_img[0]);
		item_left_name.setText(leftData.item_name);
		item_left_price.setText(leftData.price);
		if(rightData != null ){
			item_right_img.setImageResource(rightData.item_img[0]);
			item_right_name.setText(rightData.item_name);
			item_right_price.setText(rightData.price);
		}
	}
	
	
}
