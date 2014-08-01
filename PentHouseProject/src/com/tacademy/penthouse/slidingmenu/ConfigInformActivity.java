package com.tacademy.penthouse.slidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tacademy.penthouse.R;

public class ConfigInformActivity extends Activity {

	ListView lv;
	ConfigInformAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config_inform);
		
		lv = (ListView)findViewById(R.id.listView1);
		mAdapter = new ConfigInformAdapter(this);
		mAdapter.add("공지사항");
		mAdapter.add("안내??뭐드라");
		mAdapter.add("문의하기");
		mAdapter.add("");
		lv.setAdapter(mAdapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(ConfigInformActivity.this, NoticeActivity.class);
				startActivity(i);
			}
		});
		
		
	}
}
