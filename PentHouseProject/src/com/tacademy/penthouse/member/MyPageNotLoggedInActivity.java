package com.tacademy.penthouse.member;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tacademy.penthouse.R;

public class MyPageNotLoggedInActivity extends Activity {
	TextView log_in, sign_up;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mypage_not_loggedin);
		
		log_in = (TextView)findViewById(R.id.log_in_btn);
		sign_up = (TextView)findViewById(R.id.sign_up_btn);
		Intent i = getIntent();
		
		log_in.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent login = new Intent(MyPageNotLoggedInActivity.this, LogInActivity.class);
				startActivity(login);
			}
		});
		
		sign_up.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent signup = new Intent(MyPageNotLoggedInActivity.this, SignUpActivity.class);
				startActivity(signup);
			}
		});
	}
}
