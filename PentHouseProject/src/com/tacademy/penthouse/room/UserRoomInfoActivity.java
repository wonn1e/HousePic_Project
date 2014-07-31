package com.tacademy.penthouse.room;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.RoomItemsResult;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.house.HouseActivity;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;

public class UserRoomInfoActivity extends FragmentActivity {
	public static final String PARAM_USER = "uData";
	ItemLikeShowListDialog itemLikeDialog;
	ItemAdapter iAdapter;
	
	ImageView u_room_img;
	TextView u_room_name;
	TextView u_room_update_time;
	TextView u_room_intro;
	ImageView u_room_my_img;
	TextView u_room_nickname;
	TextView u_room_product_list;
	StaggeredGridView u_room_item_gridview;
	

	ImageLoader loader;
	DisplayImageOptions options;

	//UserData uData;
	UserData uData = new UserData(100, "aaa", "test user", "zzzz", 10, 12, "aa","aa","aa","aa");
	ArrayList<ItemData> iData = new ArrayList<ItemData>();
	RoomData rData = new RoomData();
	int r_num = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_info);
		itemLikeDialog = new ItemLikeShowListDialog();
		Intent i = getIntent();
		r_num = i.getIntExtra("rData", r_num);
		
		

		
		
		
		
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		
		View v = getLayoutInflater().inflate(R.layout.header_view_room_layout, null);
		u_room_img = (ImageView)v.findViewById(R.id.u_room_img);
		u_room_name = (TextView)v.findViewById(R.id.u_room_name);
		u_room_update_time = (TextView)v.findViewById(R.id.u_room_update_time); 
		u_room_my_img = (ImageView)v.findViewById(R.id.u_room_my_img);
		u_room_nickname = (TextView)v.findViewById(R.id.u_room_nickname);
		u_room_product_list = (TextView)v.findViewById(R.id.u_room_product_list);
		u_room_item_gridview = (StaggeredGridView)findViewById(R.id.gridView_room);
		
		
		
		
		
		u_room_item_gridview.addHeaderView(v);
		//누른 사용자의 Activity로 이동!
		u_room_my_img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(UserRoomInfoActivity.this, HouseActivity.class);
				i.putExtra(PARAM_USER, uData);
				startActivity(i);
			}
		});

		//닉네임도 할것인가! 두둥치
		iAdapter = new ItemAdapter(this);
		u_room_item_gridview.setAdapter(iAdapter);
		

		iAdapter.setOnAdapterItemClickListener(new ItemAdapter.OnAdapterItemClickListener() {

			@Override
			public void onItemLikeClick(View v, ItemData iData) {
				/*Toast.makeText(UserRoomInfoActivity.this, "Clicked in UserRoomInfoActivitiy", Toast.LENGTH_SHORT).show();
				
				//now unlike!!
				if(iData.item_like){
					//iData.item_like = false;
					iData.likeCnt--;
					//iData.notify();
				}
				//now like!!
				else{
					//iData.item_like = true;
					iData.likeCnt++;
					//iData.notify();

					//idata update! (ex. likeCnt, etc)

					Bundle b = new Bundle();
					b.putParcelable(ItemLikeShowListDialog.PARAM_ITEM_DATA, iData);
					b.putParcelableArray(ItemLikeShowListDialog.PARAM_ROOM_DATA, myRoomData);
					itemLikeDialog.setArguments(b);
					itemLikeDialog.show(getSupportFragmentManager(), "dialog");

				}*/
			}
		});


		u_room_item_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Item의 상세정보 Activity로 이동!
				Intent i = new Intent(UserRoomInfoActivity.this, ItemInfoActivity.class);
				i.putExtra("iData", iData.get(position));
				startActivity(i);

			}
		});
		initData();
	}
	
	private void initData(){

		NetworkManager.getInstance().getRoomInfo(this, uData.user_id, r_num, new NetworkManager.OnResultListener<RoomItemsResult>() {

			@Override
			public void onSuccess(RoomItemsResult result) {
				// TODO Auto-generated method stub
				rData = result.result.room;
				u_room_name.setText(rData.room_name);
				loader.displayImage(rData.room_img_url, u_room_img ,options);

				iData = result.result.items;
				for(int j = 0; j < iData.size(); j++){
					iAdapter.add(iData.get(j));
					//iAdapter.add(rData.items.get(j));
				}
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
