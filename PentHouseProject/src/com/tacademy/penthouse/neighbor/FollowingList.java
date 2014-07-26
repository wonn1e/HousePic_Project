package com.tacademy.penthouse.neighbor;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.UserData;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FollowingList extends PagerFragment {
	public static final int REQUEST_CODE_FOLLOWING= 0;
	ListView listview_following;
	NeighborAdapter nAdapter;
	String name, message;
	int resId;
	ActionBar actionBar;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.neighbor_layout, container, false);
		listview_following = (ListView)v.findViewById(R.id.listview_neighbor);
		nAdapter = new NeighborAdapter(getActivity());
		listview_following.setAdapter(nAdapter);
		listview_following.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				//
				Toast.makeText(getActivity(), ""+ position, Toast.LENGTH_SHORT).show();
			}
		});
		
		initData();
		return v;
	}
	
	private void initData(){
		UserData u0 = new UserData(0, "a", "aaa", "skdj", 10, 10, 1000);
		UserData u1 = new UserData(0, "ab", "aaa", "skdj", 10, 10, 1000);
		UserData u2 = new UserData(0, "abc", "aaa", "skdj", 10, 10, 1000);
		
		nAdapter.add(u0);
		nAdapter.add(u1);
		nAdapter.add(u2);
	}
	
	@Override
	public void onPageCurrent() {
		super.onPageCurrent();
		getActivity().setTitle("ÆÈ·ÎÀ×");
	}
}
