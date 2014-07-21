package com.tacademy.penthouse;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends SlidingFragmentActivity{

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.menu_frame);
        if(savedInstanceState == null){
        	getSupportFragmentManager().beginTransaction().replace(R.id.container,
        			new MainActivity())
        }
    }
}
