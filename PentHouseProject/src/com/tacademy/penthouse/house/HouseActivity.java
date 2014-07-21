package com.tacademy.penthouse.house;


import com.tacademy.penthouse.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HouseActivity extends FragmentActivity {
	private final static int CODE_ME = 0;
	private final static int CODE_OTHER = 1;
	
	TextView user_nickname, house_name, house_info;
	ImageView user_img, house_img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.house_layout);
		user_nickname = (TextView)findViewById(R.id.user_nickname);
		house_name = (TextView)findViewById(R.id.house_name);
		house_info = (TextView)findViewById(R.id.house_info);
		user_img = (ImageView)findViewById(R.id.user_img);
		house_img = (ImageView)findViewById(R.id.house_img);
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		
		
		
		
		/*btn = (Button)findViewById(R.id.btn_custom);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MyCustomDialogFragment f = new MyCustomDialogFragment();
				f.show(getSupportFragmentManager(), "dialog");
			}
		});*/
		return super.onCreateView(name, context, attrs);
	}
}
