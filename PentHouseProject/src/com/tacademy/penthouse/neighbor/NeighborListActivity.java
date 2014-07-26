package com.tacademy.penthouse.neighbor;

import com.tacademy.penthouse.R;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.TabHost;

public class NeighborListActivity extends ActionBarActivity {
	ViewPager pager;
	TabHost tabHost;
	TabsAdapter mAdapter;
	public static final String PARAM_CURRENT_TAB = "currentTab";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_neighbor_list);
		tabHost = (TabHost)findViewById(R.id.tabhost);
		pager = (ViewPager)findViewById(R.id.pager);
		tabHost.setup();
		mAdapter = new TabsAdapter(this, getSupportFragmentManager(), tabHost, pager);
		//ImageView tab1_img = new ImageView(this);
		//tab1_img.setImageResource(R.drawable.tab1_selector);
		//ImageView tab2_img = new ImageView(this);
		//tab2_img.setImageResource(R.drawable.tab2_selector)
		mAdapter.addTab(tabHost.newTabSpec("tab1").setIndicator("ÆÈ·ÎÀ×"), FollowingList.class, null);
		mAdapter.addTab(tabHost.newTabSpec("tab2").setIndicator("ÆÈ·Î¿ö"), FollowerList.class, null);
		
		if(savedInstanceState != null){
			mAdapter.onRestoreInstanceState(savedInstanceState);
			tabHost.setCurrentTabByTag(savedInstanceState.getString(PARAM_CURRENT_TAB));
		}
		
		getSupportActionBar().setDisplayShowHomeEnabled(false);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		mAdapter.onSaveInstanceState(outState);
		outState.putString(PARAM_CURRENT_TAB, tabHost.getCurrentTabTag());
	}
}
