package com.tacademy.penthouse.search;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tacademy.penthouse.R;

public class CategorySearchActivity extends ActionBarActivity {
	ActionBar actionBar;
	
	String keyword;
	EditText query;
	Button submit;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		MenuItem item = menu.findItem(R.id.menu_search);
		View v = (View)MenuItemCompat.getActionView(item);
		query = (EditText)v.findViewById(R.id.editText1);
		submit = (Button)v.findViewById(R.id.button1);
		           
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				keyword = (String)query.getText().toString();
				//Toast.makeText(MActivity.this, "This : " + keyword, Toast.LENGTH_SHORT).show();
				Intent i = new Intent(CategorySearchActivity.this,SearchResultActivity.class);
				i.putExtra("keyword", keyword);
				startActivity(i);
				query.setText("");
			}
		});
		

//		view.setOnQueryTextListener(new OnQueryTextListener() {
//			
//			@Override
//			public boolean onQueryTextSubmit(String keyword) {
//				Toast.makeText(MainActivity.this, "keyword : " + keyword, Toast.LENGTH_SHORT).show();
//				Intent i = new Intent(MainActivity.this, SearchResultActivity.class);
//				i.putExtra("keyword", keyword);
//				startActivity(i);
//				
//				return true;
//			}
//			
//			@Override
//			public boolean onQueryTextChange(String keyword) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//		});
		return true;
	}
	ListView category_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_search);
		
		
		ArrayAdapter<String> mAdapter;
		

		
		category_list = (ListView)findViewById(R.id.category_list);
		
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
		category_list.setAdapter(mAdapter);
		
		//Init Data
		mAdapter.add("침구");
		mAdapter.add("쿠션/방석");
		mAdapter.add("소파");
		mAdapter.add("테이블");
		mAdapter.add("의자");
		mAdapter.add("서랍장");
		mAdapter.add("수납용품");
		mAdapter.add("책상");
		mAdapter.add("책장");
		mAdapter.add("행거");
		mAdapter.add("화장대");
		mAdapter.add("카페트");
		mAdapter.add("벽시계");
		mAdapter.add("탁상시계");
		mAdapter.add("램프");
		mAdapter.add("액자");
		mAdapter.add("커튼/블라인드");
		mAdapter.add("장식품");
		
		category_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent i = new Intent(CategorySearchActivity.this, SearchResultActivity.class);
				String keyword = (String)category_list.getItemAtPosition(position);
				i.putExtra("keyword", keyword);
				startActivity(i);		
			}
		});
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_search:
			//Intent i = new Intent(Activity.this, SearchActivity.class);
			//startActivity(i);
			//로 보낸다!
			
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
