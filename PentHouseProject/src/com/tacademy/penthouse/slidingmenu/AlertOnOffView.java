package com.tacademy.penthouse.slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tacademy.penthouse.R;

public class AlertOnOffView extends FrameLayout{

	public AlertOnOffView(Context context) {
		super(context);
		init();
	}
	ImageView alert_icon;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.alert_on_off_view, this);
		alert_icon = (ImageView)findViewById(R.id.alert_icon);
		
	}
	
	

}
