package com.tacademy.penthouse.neighbor;

import com.tacademy.penthouse.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.View;
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
		
		Intent i = getIntent();
		int tab = i.getParcelableExtra(PARAM_CURRENT_TAB);
		if(tab == 0)
			tabHost.setCurrentTab(0);
		else if(tab == 1)
			tabHost.setCurrentTab(1);
		
		getSupportActionBar().setDisplayShowHomeEnabled(false);
	}
	
	@Override
	public View onCreateView(String name, @NonNull Context context,
			@NonNull AttributeSet attrs) {
		return super.onCreateView(name, context, attrs);
	}
	/*
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 0){
			tabHost.setCurrentTab(FollowingList.REQUEST_CODE_FOLLOWING);
		}else if(requestCode == 1){
			tabHost.setCurrentTab(FollowerList.REQUEST_CODE_FOLLOWER);
		}
	}
	*/
	@Override
	protected void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		mAdapter.onSaveInstanceState(outState);
		outState.putString(PARAM_CURRENT_TAB, tabHost.getCurrentTabTag());
	}
}
