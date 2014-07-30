package com.tacademy.penthouse.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;

public class SignUpActivity extends Activity {
	EditText emailInput, pwInput;
	String email, pw;
	Button signUp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		emailInput = (EditText)findViewById(R.id.sign_up_email);
		pwInput = (EditText)findViewById(R.id.sign_up_password);
		email = emailInput.getText().toString();
		pw = pwInput.getText().toString();

		signUp= (Button)findViewById(R.id.sign_up_btn);
		signUp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(email !=null && !email.equals("") && pw !=null && !pw.equals("")){
					signUp.setEnabled(true);
					Intent i = new Intent(SignUpActivity.this, MainActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
				}
			}
		});
	}
}
