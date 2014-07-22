package com.tacademy.penthouse;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.MenuItem;
import android.widget.TabHost;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tacademy.penthouse.slidingmenu.MenuFragment;

public class MainActivity extends SlidingFragmentActivity{

	FragmentTabHost tabHost;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.menu_frame);
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
    	}
    	
    	
    	
    }
  
    //for sliding menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    	case android.R.id.home:
    		toggle();
    		break;
    	}
    	return super.onOptionsItemSelected(item);
    }

}
