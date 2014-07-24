package com.tacademy.penthouse.room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.etsy.android.grid.StaggeredGridView;
import com.meetme.android.horizontallistview.HorizontalListView;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.item.ItemInfoActivity;

public class UserRoomInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_info);
		//Dummy DAta
		String[] t = {"aa","bb"};
		int[] img = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
		final ItemData[] iData = {new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com"), 
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com"),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com"),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com"),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com"),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com"),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com")};

		
		ImageView u_room_img;
		TextView u_room_name;
		TextView u_room_update_time;
		TextView u_room_intro;
		ImageView u_room_my_img;
		TextView u_room_nickname;
		
		TextView u_room_product_list;
		StaggeredGridView u_room_item_gridview;
		View v = getLayoutInflater().inflate(R.layout.header_view_room_layout, null);
		
		u_room_img = (ImageView)v.findViewById(R.id.u_room_img);
		u_room_name = (TextView)v.findViewById(R.id.u_room_name);
		u_room_update_time = (TextView)v.findViewById(R.id.u_room_update_time); 
		u_room_intro = (TextView)v.findViewById(R.id.u_room_intro);
		u_room_my_img = (ImageView)v.findViewById(R.id.u_room_my_img);
		u_room_nickname = (TextView)v.findViewById(R.id.u_room_nickname);
		u_room_product_list = (TextView)v.findViewById(R.id.u_room_product_list);
		u_room_item_gridview = (StaggeredGridView)findViewById(R.id.gridView_room);
	
		//누른 사용자의 Activity로 이동!
		u_room_my_img.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				///////////
				
				
			}
		});
		
		//닉네임도 할것인가! 두둥치
		ItemAdapter iAdapter;
		iAdapter = new ItemAdapter(this);
		u_room_item_gridview.setAdapter(iAdapter);
		for(int i = 0; i < iData.length; i++){
			iAdapter.add(iData[i]);	
		}
		
		
		
		
		
		u_room_item_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Item의 상세정보 Activity로 이동!
				Intent i = new Intent(UserRoomInfoActivity.this, ItemInfoActivity.class);
				i.putExtra("iData", iData[position]);
				startActivity(i);
				
			}
		});

	}
}
