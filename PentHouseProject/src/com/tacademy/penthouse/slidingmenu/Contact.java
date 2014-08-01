package com.tacademy.penthouse.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tacademy.penthouse.R;

public class Contact extends Activity {
	Spinner contactType;
	EditText getEmail, getTitle;
	TextView contactText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
	}
}
