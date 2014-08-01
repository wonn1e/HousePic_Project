package com.tacademy.penthouse.slidingmenu;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class NoticeActivity extends Activity {
	ExpandableListView lv;
	NoticeAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice);
		lv = (ExpandableListView)findViewById(R.id.notice_list);
		mAdapter = new NoticeAdapter(this);
		lv.setAdapter(mAdapter);
		init();
		for(int i = 0; i < mAdapter.getGroupCount(); i++){
			lv.expandGroup(i);
		}
	}

	private void init() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 1; j++) {
				mAdapter.put("공지사항"+i, " 내용 내용 내용"+j);
			}
		}
	}
}
