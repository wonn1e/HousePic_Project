package com.tacademy.penthouse.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.room.ItemAdapter;

public class SearchResultActivity extends Activity {
	Spinner spinner;
	ItemAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		
		spinner = (Spinner)findViewById(R.id.sort_spinner);
		mAdapter = new ItemAdapter(this);
		spinner.setAdapter(mAdapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				//선택된 항목으로 Sorting된 결과를  GridView를 뿌린다.
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		
		
		});
		
		
	}
}
