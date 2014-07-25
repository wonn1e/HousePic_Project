package com.tacademy.penthouse.item;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.tacademy.penthouse.R;

public class ItemShareDialog extends DialogFragment {
	ImageView fb, kakao, msg;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.HouseDialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog d = getDialog();
		d.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		View v = inflater.inflate(R.layout.item_share_layout, container, false);
		fb = (ImageView)v.findViewById(R.id.share_facebook);
		fb.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//facebook 연결
				
				dismiss();
			}
		});
		
		kakao = (ImageView)v.findViewById(R.id.share_kakao);
		kakao.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 카카오 연결
				
				dismiss();				
			}
		});
		
		msg = (ImageView)v.findViewById(R.id.share_textmsg);
		msg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//주소록 연결
				
				
				dismiss();
			}
		});
		
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		Dialog d = getDialog();
		d.setTitle("공유 선택");
		d.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
	}
}
