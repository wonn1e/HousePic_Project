package com.tacademy.penthouse.neighbor;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.TabHost;

public class NeighborListActivity extends ActionBarActivity {
	ViewPager pager;
	TabHost tabHost;
	TabsAdapter mAdapter;
	public static final String PARAM_CURRENT_TAB = "currentTab";
	
}
