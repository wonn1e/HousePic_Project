package com.tacademy.penthouse.item;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meetme.android.horizontallistview.HorizontalListView;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.browser.BrowserActivity;
import com.tacademy.penthouse.editimgdialog.EditImgActivity;
import com.tacademy.penthouse.editimgdialog.RegisterNewImageActivity;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.itemlike.CreateNewRoomDialog;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class ItemInfoActivity extends FragmentActivity {
	ItemData iData;
	ItemLikeShowListDialog itemLikeDialog;

	TextView item_name_brand, item_like_count, item_price, item_material, item_size;
	ImageView show_item_like;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);
		
		final RoomData[] myRoomData = {
				new RoomData(1,1,"house1",R.drawable.ic_launcher,"방설명1",true),
				new RoomData(2,2,"house2",R.drawable.ic_launcher,"방설명2",true),
				new RoomData(3,3,"house3",R.drawable.ic_launcher,"방설명3",true)
		};
		
		ViewPager mPager;
		HorizontalListView hlv_s_item;
		ItemFragmentAdapter mAdapter;
		ItemRecommandAdapter iAdapter;
		PageIndicator mIndicator;
		itemLikeDialog = new ItemLikeShowListDialog();
		Button item_share_btn;
		Button item_buy_btn;
		//		String[] t = {"aa","bb"};
		//		int[] img = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
		//		Integer[] img2= {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
		//		ItemData iData= new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img);
		iData = new ItemData();
		Intent i = getIntent();
		iData = i.getParcelableExtra("iData");
		
		
		//data that is passed to other Fragments!
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
		
		show_item_like = (ImageView)findViewById(R.id.item_like);
		show_item_like.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(ItemInfoActivity.this, "clicked", Toast.LENGTH_SHORT).show();
				
				//now unlike!!
				if(iData.item_like){
					iData.item_like = false;
					iData.likeCnt--;
					show_item_like.setImageResource(R.drawable.tulips);
					//iData.notify();
				}
				//now like!!
				else{
					//iData.item_like = true;
					//iData.likeCnt++;
					//show_item_like.setImageResource(R.drawable.ic_launcher);
					//iData.notify();
					
					//idata update! (ex. likeCnt, etc)
					
					Bundle b = new Bundle();
					b.putParcelable(ItemLikeShowListDialog.PARAM_ITEM_DATA, iData);
					b.putParcelableArray(ItemLikeShowListDialog.PARAM_ROOM_DATA, myRoomData);
					itemLikeDialog.setArguments(b);
					itemLikeDialog.show(getSupportFragmentManager(), "dialog");
				}
			}
		});
		
		item_price = (TextView)findViewById(R.id.item_price);
		item_share_btn = (Button)findViewById(R.id.item_share_btn);
		item_share_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ItemShareDialog d = new ItemShareDialog();
				d.show(getSupportFragmentManager(), "item_share");
			}
		});

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

		initData();
		
	}

	//to get image for room
	/*public void createNewRoom(int id){
		switch(id){
		case CreateNewRoomDialog.ID_IMAGE:
			Intent i = new Intent(ItemInfoActivity.this, RegisterNewImageActivity.class);
			startActivity(i);
			break;
		}
	}*/
	
	private void initData(){
		item_material = (TextView)findViewById(R.id.item_material);
		item_size = (TextView)findViewById(R.id.item_size);
		Toast.makeText(ItemInfoActivity.this, "name : " + iData.item_name, Toast.LENGTH_SHORT).show();
		item_name_brand.setText(iData.item_name);
		item_like_count.setText(""+iData.likeCnt);
		item_price.setText(iData.price);
		item_material.setText(iData.material);
		if(iData.item_like)
			show_item_like.setImageResource(R.drawable.ic_launcher);
		else
			show_item_like.setImageResource(R.drawable.tulips);
		//		item_size.setText(iData.)

	}
}