package com.tacademy.penthouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.house.HouseActivity;
import com.tacademy.penthouse.ranking.RankingActivity;
import com.tacademy.penthouse.search.CategorySearchActivity;
import com.tacademy.penthouse.slidingmenu.MenuFragment;

public class MainActivity extends SlidingFragmentActivity{
	
	FragmentTabHost tabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.menu_frame);
		//startActivity(new Intent(this, SplashActivity.class));
		if(savedInstanceState == null){
			getSupportFragmentManager().beginTransaction().replace(R.id.menu_container, 
					new MenuFragment()).commit();
		}

		tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("MD"), Tab1MDFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Everyone"), Tab2EveryoneFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Friends"), Tab3FriendsFragment.class, null);
		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals("tab1")) {

				} else if (tabId.equals("tab2")) {

				} else if (tabId.equals("tab3")) {

				}
			}
		});

		SlidingMenu sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		getSupportActionBar().setHomeButtonEnabled(true);
		setSlidingActionBarEnabled(false);

	}

	//for fragment tab
	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
		if(fragment instanceof Tab1MDFragment){
			Tab1MDFragment f = (Tab1MDFragment)fragment;
			//register listener;


		}else if(fragment instanceof Tab2EveryoneFragment){
			Tab2EveryoneFragment f = (Tab2EveryoneFragment)fragment;


		}else if(fragment instanceof Tab3FriendsFragment){
			Tab3FriendsFragment f = (Tab3FriendsFragment)fragment;



		}



	}

	//for sliding menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			toggle();
			break;
		case R.id.menu_search:
			Intent i = new Intent(MainActivity.this, CategorySearchActivity.class);
			startActivity(i);
			//로 보낸다!

			break;
		}
		return super.onOptionsItemSelected(item);
	}

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
		return true;
	}
	UserData myData;
	UserData uData[];
//
//	final UserData myData = 	new UserData(1, "a","a","a",1,1, R.drawable.tulips,"XXX's House", "XX의 집에 오신걸 환영", "aa");
//	final UserData uData[] = {	new UserData(2, "b1","b1","b1",2,2,R.drawable.penguins,"YYY1's House","Welcome","bb"),
//							  	new UserData(3, "b2","b2","b2",2,2,R.drawable.penguins,"YYY2's House","Welcome","bb"),
//							  	new UserData(4, "b3","b3","b3",2,2,R.drawable.penguins,"YYY3's House","Welcome","bb"),
//							  	new UserData(5, "b4","b4","b4",2,2,R.drawable.penguins,"YYY4's House","Welcome","bb"),
//							  	new UserData(6, "b5","b5","b5",2,2,R.drawable.penguins,"YYY5's House","Welcome","bb"),
//							  	new UserData(7, "b6","b6","b6",2,2,R.drawable.penguins,"YYY6's House","Welcome","bb"),
//							  	new UserData(8, "b7","b7","b7",2,2,R.drawable.penguins,"YYY7's House","Welcome","bb"),
//							 	new UserData(9, "b8","b8","b8",2,2,R.drawable.penguins,"YYY8's House","Welcome","bb")	};
	
	public void actionMenu(int menuId) {
		switch(menuId) {
		case MenuFragment.ID_MYHOUSE:
			//if(uData.user_num == myData.user_num){
				Intent iMyHouse = new Intent(MainActivity.this, HouseActivity.class);
				iMyHouse.putExtra(HouseActivity.PARAM_USER_DATA, myData);
				iMyHouse.putExtra(HouseActivity.PARAM_MY_DATA, myData);
				startActivity(iMyHouse);
				getSlidingMenu().showContent();
			//}
			break;
		case MenuFragment.ID_RANK:
			Intent iRank = new Intent(MainActivity.this, RankingActivity.class);
			startActivity(iRank);
			getSlidingMenu().showContent();
			break;
		}
	}

}
