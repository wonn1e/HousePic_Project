package com.tacademy.penthouse.search;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.CreateNewRoomActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.room.ItemAdapter;
import com.tacademy.penthouse.room.MyRoomInfoActivity;

public class SearchResultActivity extends ActionBarActivity {
	public static final int REQUEST_NEW_ROOM_IN_SEARCH = 0;
	
	//ItemData 생성부분

//	String[] t = {"aa","bb"};
//	int[] img = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
//	Integer[] img2= {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
//	
//	final ItemData[] iData = {new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", true),
//			new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false),
//			new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false),
//			new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com",true),
//			new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", true),
//			new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false),
//			new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", false)};
//	final RoomData[] myRoomData = {
//			new RoomData(1,1,"house1",R.drawable.ic_launcher,"방설명1",true),
//			new RoomData(2,2,"house2",R.drawable.ic_launcher,"방설명2",true),
//			new RoomData(3,3,"house3",R.drawable.ic_launcher,"방설명3",true)
//	};	
	UserData myData;
	ItemData[] iData;
	RoomData[] myRoomData;
	
	ItemLikeShowListDialog itemLikeDialog;
	Spinner sort_spinner;
	GridView item_gridview;

	ArrayAdapter<String> sAdapter;
	ItemAdapter iAdapter;
	ActionBar actionBar;
	String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		
		//myData = new UserData(10, "www", "kw", "password", 100, 120, 12345,"kw's house", "welcome to kw's home", "aa");
		
		itemLikeDialog = new ItemLikeShowListDialog();
		Intent i = getIntent();
		title = i.getStringExtra("keyword");

		actionBar = getSupportActionBar();
		actionBar.setTitle(title);


		//검색어로 쿼리를 날려서 Item을 받아야한다!!!

		sort_spinner = (Spinner)findViewById(R.id.sort_spinner);
		sAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,new ArrayList<String>());
		sAdapter.add("모두보기");
		sAdapter.add("인기순");
		sAdapter.add("저 가격 순");
		sAdapter.add("고 가격 순");
		sAdapter.add("모던");
		sAdapter.add("북유럽");
		sAdapter.add("내추럴");
		sAdapter.add("클래식");
		sAdapter.add("한국적");
		sAdapter.add("유니크");

		sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sort_spinner.setAdapter(sAdapter);
		//Spinner 설정
		sort_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(SearchResultActivity.this, "selected item : " + position, Toast.LENGTH_SHORT).show();
				//Sorting에 대한 처리를 해줌!
			}	

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		iAdapter = new ItemAdapter(this);
		item_gridview = (GridView)findViewById(R.id.item_gridview);
		item_gridview.setAdapter(iAdapter);
		for(int j = 0; j < iData.length; j++){
			iAdapter.add(iData[j]);	
		}


		iAdapter.setOnAdapterItemClickListener(new ItemAdapter.OnAdapterItemClickListener() {

			@Override
			public void onItemLikeClick(View v, ItemData data) {
				//now unlike!!
				if(data.item_like){
					Toast.makeText(SearchResultActivity.this, "unlike in SearchResult" , Toast.LENGTH_SHORT).show();
					data.item_like = false;
					data.likeCnt--;
					/*
					 * item data update
					 */
				}

				//now like!!
				else{
					Bundle b = new Bundle();
					b.putParcelable(ItemLikeShowListDialog.PARAM_ITEM_DATA, data);
					b.putParcelableArray(ItemLikeShowListDialog.PARAM_ROOM_DATA, myRoomData);
					itemLikeDialog.setArguments(b);
					itemLikeDialog.show(getSupportFragmentManager(), "dialog");

					itemLikeDialog.setOnRoomSelectedListener(new ItemLikeShowListDialog.OnRoomSelectedListener() {

						@Override
						public void onRoomSelected(boolean roomSelected) {
							Toast.makeText(SearchResultActivity.this, "item in room!! (SearchResult)", Toast.LENGTH_SHORT).show();
							/*
							 * Item Data update
							 */
						}
					});

					itemLikeDialog.setOnCreateSelectedListener(new ItemLikeShowListDialog.OnCreateSelectedListener() {

						@Override
						public void onCreateSelected(boolean roomSelected) {
							Toast.makeText(SearchResultActivity.this, "create new room SearchResult", Toast.LENGTH_SHORT).show();
							Intent i = new Intent(SearchResultActivity.this , CreateNewRoomActivity.class);
							i.putExtra("iData", iData);
							//pass on myData (UserData) 
							i.putExtra("myData", myData);
							startActivityForResult(i, REQUEST_NEW_ROOM_IN_SEARCH);
							onResume();
						}
					});
				}
			}
		});

		item_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(SearchResultActivity.this, ItemInfoActivity.class);
				i.putExtra("iData", iData[position]);
				startActivity(i);
			}
		});

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_NEW_ROOM_IN_SEARCH && resultCode == Activity.RESULT_OK){
			Toast.makeText(SearchResultActivity.this, "item in new room from SearchResult!", Toast.LENGTH_SHORT).show();
		}
	}
}
