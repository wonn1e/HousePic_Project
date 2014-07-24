package com.tacademy.penthouse.slidingmenu;

import com.tacademy.penthouse.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MenuFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.menu_fragment, container, false);
		
		TextView my_page = (TextView)v.findViewById(R.id.my_page);
		my_page.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			//	((MainActivity)getActivity()).switchTwoFragment();
				//Toast.makeText(getActivity(), "to my page", Toast.LENGTH_SHORT).show();
			}
		});
		
//		tv = (TextView)v.findViewById(R.id.show_recommendation); //to home page
//		tv.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(getActivity(), "to home page", Toast.LENGTH_SHORT).show();
//			}
//		});
//		
		TextView show_ranking = (TextView)v.findViewById(R.id.show_ranking);
		show_ranking.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		return v;
	}
}
