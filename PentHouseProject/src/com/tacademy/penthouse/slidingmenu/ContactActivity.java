package com.tacademy.penthouse.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.penthouse.R;

public class ContactActivity extends Activity {
	Spinner contactType;
	EditText getEmail, getTitle,getContact;
	ArrayAdapter<String> mAdapter;
	String type;
	String[] items = {"문의유형 선택","기능 추가 문의", "오류 / 버그 신고하기","불편 사항", "회원탈퇴하기", "기타"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		contactType = (Spinner)findViewById(R.id.contactType);
		getEmail = (EditText)findViewById(R.id.emailInfo);
		getTitle = (EditText)findViewById(R.id.titleInfo);
		getContact = (EditText)findViewById(R.id.contactInfo);
		
		mAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items);
		mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		contactType.setAdapter(mAdapter);
		contactType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(ContactActivity.this, "selected item postion : " + position, Toast.LENGTH_SHORT).show();
				type = items[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
	}
}
