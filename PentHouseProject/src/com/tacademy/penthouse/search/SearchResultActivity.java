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
import com.tacademy.penthouse.room.ItemAdapter;

public class SearchResultActivity extends ActionBarActivity {
	//ItemData 생성부분
			String[] t = {"aa","bb"};
			int[] img = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
			Integer[] img2= {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
			
			final ItemData[] iData = {new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img), new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img),new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img),new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img),new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img),
					new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img),new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img)};
	
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
		
		
		
		
	}
}
