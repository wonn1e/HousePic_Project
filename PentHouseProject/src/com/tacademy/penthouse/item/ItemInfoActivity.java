package com.tacademy.penthouse.item;

import com.meetme.android.horizontallistview.HorizontalListView;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);
		HorizontalListView hlv_item;
		HorizontalListView hlv_s_item;
		
		TextView item_name_brand;
		TextView item_like_count;
		ImageView item_like;
		TextView item_price;
		Button item_share_btn;
		Button item_buy_btn;
		TextView item_material;
		TextView item_size;
	}
}
