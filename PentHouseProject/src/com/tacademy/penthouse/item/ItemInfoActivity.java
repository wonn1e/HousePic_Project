package com.tacademy.penthouse.item;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meetme.android.horizontallistview.HorizontalListView;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.browser.BrowserActivity;
import com.tacademy.penthouse.entity.ItemData;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class ItemInfoActivity extends FragmentActivity {

	ItemData iData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);
		ViewPager mPager;
		HorizontalListView hlv_s_item;
		ItemFragmentAdapter mAdapter;
		ItemRecommandAdapter iAdapter;
	    PageIndicator mIndicator;
	    
		TextView item_name_brand,  item_like_count, item_price, item_material, item_size;
		ImageView item_like;
		Button item_share_btn;
		Button item_buy_btn;
//		String[] t = {"aa","bb"};
//		int[] img = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
//		Integer[] img2= {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
//		ItemData iData= new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img);
		iData = new ItemData();
		Intent i = getIntent();
		iData = i.getParcelableExtra("iData");
		
		mAdapter = new ItemFragmentAdapter(getSupportFragmentManager(), iData);

	    mPager = (ViewPager)findViewById(R.id.pager);
	    mPager.setAdapter(mAdapter);
	    
	    mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
	    mIndicator.setViewPager(mPager);
	    
	    iAdapter = new ItemRecommandAdapter(this);
	    hlv_s_item = (HorizontalListView) findViewById(R.id.horizontalListView2);
	    
	    hlv_s_item.setAdapter(iAdapter);
	    for(int j = 0; j < iData.item_img.length; j++){
	    iAdapter.add(iData.item_img[j]);
	    }
	    
	    
		item_name_brand = (TextView) findViewById(R.id.item_name_brand);
		item_like_count = (TextView) findViewById(R.id.item_like_count);
		item_like = (ImageView)findViewById(R.id.item_like);
		item_price = (TextView)findViewById(R.id.item_price);
		item_share_btn = (Button)findViewById(R.id.item_share_btn);
		
		item_buy_btn = (Button)findViewById(R.id.item_buy_btn);
		item_buy_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(iData.link != null && !iData.link.equals("")){
					Intent i = new Intent(ItemInfoActivity.this, BrowserActivity.class);
					i.setData(Uri.parse(iData.link));
					startActivity(i);
				}
			}
		});
		
		item_material = (TextView)findViewById(R.id.item_material);
		item_size = (TextView)findViewById(R.id.item_size);
		Toast.makeText(ItemInfoActivity.this, "name : " + iData.item_name, Toast.LENGTH_SHORT).show();
		
		
		item_name_brand.setText(iData.item_name);
		item_like_count.setText(""+iData.likeCnt);
		item_price.setText(iData.price);
		item_material.setText(iData.material);
//		item_size.setText(iData.)
		
	}
}