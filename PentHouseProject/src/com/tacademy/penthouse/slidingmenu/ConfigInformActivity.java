package com.tacademy.penthouse.slidingmenu;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
		mAdapter.add("��������");
		mAdapter.add("�ȳ�??�����");
		mAdapter.add("�����ϱ�");
		mAdapter.add("");
		lv.setAdapter(mAdapter);
		
		
		
		
	}
}
