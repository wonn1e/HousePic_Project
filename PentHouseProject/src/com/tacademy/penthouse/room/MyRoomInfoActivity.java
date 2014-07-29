package com.tacademy.penthouse.room;

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
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;

public class MyRoomInfoActivity extends FragmentActivity {
	ItemLikeShowListDialog itemLikeDialog;
	MyItemAdapter iAdapter;
	
	ImageView room_img;
	TextView room_name;
	TextView room_update_time;
	TextView room_intro;
	TextView room_product_list;
	ItemData[] iData;
	RoomData[] myRoomData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_room_info);

		StaggeredGridView myroom_item_gridview;
		itemLikeDialog = new ItemLikeShowListDialog();

//		//ItemData 생성부분
//		String[] t = {"aa","bb"};
//		int[] img = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
//		final ItemData[] iData = {new ItemData(1,1,"aa","a1","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", true),
//				new ItemData(1,1,"aa","a2","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false),
//				new ItemData(1,1,"aa","a3","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false),
//				new ItemData(1,1,"aa","a4","aa","aa","aa",t,1,"aa",img, "http://www.naver.com",true),
//				new ItemData(1,1,"aa","a5","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", true),
//				new ItemData(1,1,"aa","a6","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false),
//				new ItemData(1,1,"aa","a7","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false)};
//		final RoomData[] myRoomData = {
//				new RoomData(1,1,"house1",R.drawable.ic_launcher,"방설명1",true),
//				new RoomData(2,2,"house2",R.drawable.ic_launcher,"방설명2",true),
//				new RoomData(3,3,"house3",R.drawable.ic_launcher,"방설명3",true)
//		};
//		//

		//	GridView room_item_gridview;

		View v = getLayoutInflater().inflate(R.layout.header_view_myroom_layout, null);
		room_img = (ImageView)v.findViewById(R.id.room_img);
		room_name = (TextView)v.findViewById(R.id.room_name);
		room_update_time = (TextView)v.findViewById(R.id.room_update_time); 
		room_intro = (TextView)v.findViewById(R.id.room_intro);
		room_product_list = (TextView)v.findViewById(R.id.room_product_list);

		myroom_item_gridview = (StaggeredGridView)findViewById(R.id.gridView_myroom);
		myroom_item_gridview.addHeaderView(v);

		//Adapter
		iAdapter = new MyItemAdapter(this);
		myroom_item_gridview.setAdapter(iAdapter);
		for(int i = 0; i < iData.length; i++){
			iAdapter.add(iData[i]);	
		}

		iAdapter.setOnAdapterItemClickListener(new MyItemAdapter.OnAdapterItemClickListener() {

			@Override
			public void onItemLikeClick(View v, ItemData data) {
				Toast.makeText(MyRoomInfoActivity.this, "Clicked in MyRoomInfoActivitiy", Toast.LENGTH_SHORT).show();

				//now unlike!!
				if(data.item_like){
					data.item_like = false;
					data.likeCnt--;
					//iData.notify();
				}
				//now like!!
				else{
					Toast.makeText(MyRoomInfoActivity.this, "like true", Toast.LENGTH_SHORT).show();
					data.item_like = true;
					data.likeCnt++;
					//iData.notify();

					//idata update! (ex. likeCnt, etc)

					Bundle b = new Bundle();
					b.putParcelable(ItemLikeShowListDialog.PARAM_ITEM_DATA, data);
					b.putParcelableArray(ItemLikeShowListDialog.PARAM_ROOM_DATA, myRoomData);
					itemLikeDialog.setArguments(b);
					itemLikeDialog.show(getSupportFragmentManager(), "dialog");

				}

			}

			@Override
			public void onItemMoveClick(View v, ItemData data) {
				Toast.makeText(MyRoomInfoActivity.this, "Click in room ", Toast.LENGTH_SHORT).show();
				Bundle b = new Bundle();
				b.putParcelable(ItemLikeShowListDialog.PARAM_ITEM_DATA, data);
				b.putParcelableArray(ItemLikeShowListDialog.PARAM_ROOM_DATA, myRoomData);
				itemLikeDialog.setArguments(b);
				itemLikeDialog.show(getSupportFragmentManager(), "dialog");
				
				//방을 선택 후 그 방안의 아이템을 삭제!
			}
		});

		myroom_item_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Item의 상세정보 Activity로 이동!
				if(position != 0){
					Intent i = new Intent(MyRoomInfoActivity.this, ItemInfoActivity.class);
					i.putExtra("iData", iData[position]);
					startActivityForResult(i, 0);
				}
			}
		});

	}
}
