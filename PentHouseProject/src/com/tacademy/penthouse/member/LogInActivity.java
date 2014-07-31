package com.tacademy.penthouse.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;

public class LogInActivity extends Activity {
	EditText emailInput, pwInput;
	String email, pw;
	CheckBox autoLogIn;
	TextView findPw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in_dialog_fragment);
		
		emailInput = (EditText)findViewById(R.id.input_email);
		email = emailInput.getText().toString();
		pwInput= (EditText)findViewById(R.id.inpu_password);
		pw = pwInput.getText().toString();
		//자동 로그인 처리
		autoLogIn = (CheckBox)findViewById(R.id.auto_login_checkbox);
		findPw = (TextView)findViewById(R.id.find_password_btn);
		findPw.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(LogInActivity.this, FindPasswordFragment.class);
				startActivity(i);
			}
		});
		
		Button btn = (Button)findViewById(R.id.log_in_btn_LogIn);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(email != null && !email.equals("") && pw != null && !pw.equals("")){
					Intent i = new Intent(LogInActivity.this, MainActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
				}
					
			}
		});
		
	}
}
