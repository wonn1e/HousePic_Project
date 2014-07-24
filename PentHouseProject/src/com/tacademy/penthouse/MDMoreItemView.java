package com.tacademy.penthouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MDMoreItemView extends FrameLayout {

	public MDMoreItemView(Context context) {
		super(context);
		init();
	}
	
	TextView num_item_left, more;
	////call data
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_moreitem_view, this);
		num_item_left = (TextView)findViewById(R.id.num_item_left);
		more = (TextView)findViewById(R.id.textView1);
	}
	
	public void setItemData(){
		num_item_left.setText(""+15);
		more.setText("´õº¸±â");
	}

}
