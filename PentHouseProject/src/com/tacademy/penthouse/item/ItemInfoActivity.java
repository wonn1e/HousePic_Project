package com.tacademy.penthouse.item;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.itemlike.CreateNewRoomActivity;
import com.tacademy.penthouse.itemlike.CreateNewRoomDialog;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.ranking.RankingActivity;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class ItemInfoActivity extends FragmentActivity {
	
	public static final int REQUEST_NEW_ROOM_IN_ITEMINFO = 0;
	
	ItemData iData;
	UserData myData;
	ItemLikeShowListDialog itemLikeDialog;

	TextView item_name_brand, item_like_count, item_price, item_material, item_size;
	ImageView show_item_like;
	RoomData[] myRoomData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);
//		final RoomData[] myRoomData = {
//				new RoomData(1,1,"house1",R.drawable.ic_launcher,"방설명1",true),
//				new RoomData(2,2,"house2",R.drawable.ic_launcher,"방설명2",true),
//				new RoomData(3,3,"house3",R.drawable.ic_launcher,"방설명3",true)
//		};
		
		ViewPager mPager;
		HorizontalListView hlv_s_item;
		ItemFragmentAdapter mAdapter;
		ItemRecommandAdapter iAdapter;
		PageIndicator mIndicator;
		itemLikeDialog = new ItemLikeShowListDialog();
		Button item_share_btn;
		Button item_buy_btn;
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
		for(int j = 0; j < iData.item_img_url.length; j++){
			iAdapter.add(iData.item_img_url[j]);
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
					Toast.makeText(ItemInfoActivity.this, "unlike in ItemInfoActivity", Toast.LENGTH_SHORT).show();
					/*
					 * item data update
					 */
				}
				//now like!!
				else{
					//idata update! (ex. likeCnt, etc)
					
					Bundle b = new Bundle();
					b.putParcelable(ItemLikeShowListDialog.PARAM_ITEM_DATA, iData);
					b.putParcelableArray(ItemLikeShowListDialog.PARAM_ROOM_DATA, myRoomData);
					itemLikeDialog.setArguments(b);
					itemLikeDialog.show(getSupportFragmentManager(), "dialog");
	
					itemLikeDialog.setOnRoomSelectedListener(new ItemLikeShowListDialog.OnRoomSelectedListener() {
						
						@Override
						public void onRoomSelected(boolean roomSelected) {
							Toast.makeText(ItemInfoActivity.this, "item in room!! (ItemInfoAct)", Toast.LENGTH_SHORT).show();
							/*
							 * Item Data update
							 */
						}
					});
					
					itemLikeDialog.setOnCreateSelectedListener(new ItemLikeShowListDialog.OnCreateSelectedListener() {
						
						@Override
						public void onCreateSelected(boolean roomSelected) {
							Toast.makeText(ItemInfoActivity.this, "create new room ItemInfoActivity", Toast.LENGTH_SHORT).show();
							Intent i = new Intent(ItemInfoActivity.this , CreateNewRoomActivity.class);
							i.putExtra("iData", iData);
							//pass on myData (UserData) 
							i.putExtra("myData", myData);
							startActivityForResult(i, REQUEST_NEW_ROOM_IN_ITEMINFO);
							onResume();
						}
					});
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

		hlv_s_item.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//initData(iData.recommand_items[position]);
				//서버에서 iData를 받아서 넘긴다!
			}
		});
		initData(iData);
		
	}
	
	private void initData(ItemData data){
		item_material = (TextView)findViewById(R.id.item_material);
		item_size = (TextView)findViewById(R.id.item_size);
		Toast.makeText(ItemInfoActivity.this, "name : " + data.item_name, Toast.LENGTH_SHORT).show();
		item_name_brand.setText(data.item_name);
		item_like_count.setText(""+data.likeCnt);
		item_price.setText(data.price);
		item_material.setText(data.material);
		if(data.item_like)
			show_item_like.setImageResource(R.drawable.ic_launcher);
		else
			show_item_like.setImageResource(R.drawable.tulips);
		//		item_size.setText(data.)

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_NEW_ROOM_IN_ITEMINFO && resultCode == Activity.RESULT_OK){
			Toast.makeText(ItemInfoActivity.this, "item in new room from itemInfo!", Toast.LENGTH_SHORT).show();
		}
	}
}