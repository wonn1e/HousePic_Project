package com.tacademy.penthouse.neighbor;

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

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.UserData;

public class FollowerList extends PagerFragment {
	public static final int REQUEST_CODE_FOLLOWER= 0;
	ListView listview_follower;
	NeighborAdapter nAdapter;
	String name, message;
	int resId;
	ActionBar actionBar;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.neighbor_list_layout, container, false);
		listview_follower = (ListView)v.findViewById(R.id.listview_neighbor);
		nAdapter = new NeighborAdapter(getActivity());
		listview_follower.setAdapter(nAdapter);
		listview_follower.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				//
				Toast.makeText(getActivity(), "follower "+ position, Toast.LENGTH_SHORT).show();
			}
		});
		
		initData();
		return v;
	}
	
	private void initData(){
		UserData u0 = new UserData(0, "a", "a", "skdj", 10, 10, 1000);
		UserData u1 = new UserData(1, "ab", "aa", "skdj", 10, 10, 1000);
		UserData u2 = new UserData(2, "abc", "aaa", "skdj", 10, 10, 1000);
		UserData u3 = new UserData(3, "abd", "aaaa", "skdj", 10, 10, 1000);
		
		nAdapter.add(u0);
		nAdapter.add(u1);
		nAdapter.add(u2);
		nAdapter.add(u3);
	}
	
	@Override
	public void onPageCurrent() {
		super.onPageCurrent();
		getActivity().setTitle("ÆÈ·Î¾î");
	}
}
