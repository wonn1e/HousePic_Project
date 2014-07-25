package com.tacademy.penthouse.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;

public class ItemView extends FrameLayout{

	public ItemView(Context context) {
		super(context);
		init();
	}

	ItemData iData;
	ImageView item_img;
	TextView item_name;
	ImageView item_like;
	TextView item_price;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.item_view, this);
		item_img = (ImageView)findViewById(R.id.item_img);
		item_name = (TextView)findViewById(R.id.item_name);
		item_like = (ImageView)findViewById(R.id.item_like);
		//item_price = (TextView)findViewById(R.id.item_price);	
		
		/*item_like.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//여기서 처리하나요~~~~~?
				//좋아요 Count ++;
				Toast.makeText(getContext(), "Cliked" + iData.likeCnt, Toast.LENGTH_SHORT).show();
			}
		});*/
	}
	
	
	public void setItemData(ItemData data){
		iData = data;
		item_img.setImageResource(iData.item_img[0]);
		//첫번째 이미지를 나오게 한다.
	}
}