package com.tacademy.penthouse.house;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.tacademy.penthouse.R;

public class EditHouseName extends DialogFragment {
	EditText editHousename;
	String editValue = "";
	
	public interface OnReceiveHousenameListener{
		public void onReceiveHousename(String name);
	}
	
	OnReceiveHousenameListener mListener;
	
	public void setOnReceiveHousenameListener(OnReceiveHousenameListener listener){
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
		View v= inflater.inflate(R.layout.edit_house_name_layout, container, false);
		editHousename = (EditText)v.findViewById(R.id.edit_housename);
		editHousename.setText(editValue);
		Button btn = (Button)v.findViewById(R.id.name_changed_btn);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text = editHousename.getText().toString();
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
		d.setTitle("하우스 이름 변경하기");
		d.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
	}

}
