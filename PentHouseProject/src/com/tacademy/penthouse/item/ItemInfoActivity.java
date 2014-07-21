package com.tacademy.penthouse.item;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.meetme.android.horizontallistview.HorizontalListView;
import com.tacademy.penthouse.R;

public class ItemInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);
		ViewPager pager;
		HorizontalListView hlv_s_item;
		
		TextView item_name_brand;
		TextView item_like_count;
		ImageView item_like;
		TextView item_price;
		Button item_share_btn;
		Button item_buy_btn;
		TextView item_material;
		TextView item_size;
		
		item_name_brand = (TextView) findViewById(R.id.item_name);
		item_like_count = (TextView) findViewById(R.id.item_like_count);
		item_like = (ImageView)findViewById(R.id.item_like);
		item_price = (TextView)findViewById(R.id.item_price);
		item_share_btn = (Button)findViewById(R.id.item_share_btn);
		item_buy_btn = (Button)findViewById(R.id.item_buy_btn);
		item_material = (TextView)findViewById(R.id.item_material);
		item_size = (TextView)findViewById(R.id.item_size);
		
		
		
	}
}
