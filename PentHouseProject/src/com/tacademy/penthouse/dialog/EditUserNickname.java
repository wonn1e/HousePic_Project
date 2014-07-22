package com.tacademy.penthouse.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.UserData;

public class EditUserNickname extends DialogFragment {
	
	//public final static String PARAM_ME ="me";
	public final static String PARAM_RESULT = "new nickname";
	private static final String TAG = "Edit Nickname Fragment";
	
	EditText editNickname;
	String message ="";
	String editValue = "";
	
	public interface OnReceiveMessageListener{
		public void onReceiveMessage(String message);
	}
	
	OnReceiveMessageListener mListener;
	
	public void setOnReceiveMessageListener(OnReceiveMessageListener listener){
		mListener = listener;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(android.app.DialogFragment.STYLE_NO_TITLE, R.style.UserNickname);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog d = getDialog();
		d.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		View v= inflater.inflate(R.layout.edit_user_nickname_layout, container, false);
		editNickname = (EditText)v.findViewById(R.id.edit_nickname);
		editNickname.setText(editValue);
		
		//Intent i = getActivity().getIntent();
		//UserData uData = i.getParcelableExtra(PARAM_ME);
		
		Button btn = (Button)v.findViewById(R.id.button1);		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				String newName = editNickname.getText().toString();
//				Intent data = new Intent();
//				data.putExtra(PARAM_RESULT, newName);
//				
//				setResult(Activity.RESULT_OK, data);
				String text = editNickname.getText().toString();
				if(mListener != null){
					mListener.onReceiveMessage(text);
				}
			//	dismiss();
			}
		});
		return v;
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
}
