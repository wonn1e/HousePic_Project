package com.tacademy.penthouse;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tacademy.penthouse.slidingmenu.MenuFragment;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends SlidingFragmentActivity{

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.menu_frame);
        if(savedInstanceState == null){
        	getSupportFragmentManager().beginTransaction().replace(R.id.container,
        			new MainFragment()).commit();
        	getSupportFragmentManager().beginTransaction().replace(R.id.menu_container, 
        			new MenuFragment()).commit();
        }
        
        SlidingMenu sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT_RIGHT);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		getSupportActionBar().setHomeButtonEnabled(true);
		setSlidingActionBarEnabled(false);
    }
    
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
