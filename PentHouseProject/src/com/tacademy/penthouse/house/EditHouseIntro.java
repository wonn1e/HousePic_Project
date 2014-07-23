package com.tacademy.penthouse.house;

import com.tacademy.penthouse.R;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;

public class EditHouseIntro extends DialogFragment {
	EditText editHouseIntro;
	String editValue = "";
	
	public interface OnReceiveHouseintroListener{
		public void onReceiveHousename(String intro);
	}
	
	OnReceiveHouseintroListener mListener;
	
	public void setOnReceiveHouseintroListener(OnReceiveHouseintroListener listener){
		mListener = listener;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.HouseDialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog d  = getDialog();
		d.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		View v= inflater.inflate(R.layout.edit_house_intro_layout, container, false);
		editHouseIntro = (EditText)v.findViewById(R.id.edit_houseintro);
		editHouseIntro.setText(editValue);
		Button btn = (Button)v.findViewById(R.id.intro_changed_btn);
		btn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				String text = editHouseIntro.getText().toString();
				if(mListener != null){
					mListener.onReceiveHousename(text);
				}
				dismiss();
			}
		});
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		Dialog d= getDialog();
		d.setTitle("하우스 소개 변경하기");
		d.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
	}
}
