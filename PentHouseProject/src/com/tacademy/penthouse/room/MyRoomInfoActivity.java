package com.tacademy.penthouse.room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.item.ItemInfoActivity;

public class MyRoomInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_room_info);

		StaggeredGridView myroom_item_gridview;

		//ItemData 생성부분
		String[] t = {"aa","bb"};
		int[] img = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
		final ItemData[] iData = {new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", true),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com",true),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", true),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false),
				new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false)};

		//

		//Layout Item 선언, 생성부 
		ImageView room_img;
		TextView room_name;
		TextView room_update_time;
		TextView room_intro;
		TextView room_product_list;
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
		ItemAdapter iAdapter;
		iAdapter = new ItemAdapter(this);
		myroom_item_gridview.setAdapter(iAdapter);
		for(int i = 0; i < iData.length; i++){
			iAdapter.add(iData[i]);	
		}

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
