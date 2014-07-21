package com.tacademy.penthouse.ranking;

import java.util.ArrayList;

import com.tacademy.penthouse.entity.UserData;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RankUserAdapter extends BaseAdapter {

	ArrayList<UserData> list = new ArrayList<UserData>();
	Context mContext;
	
	public RankUserAdapter(Context context){
		mContext = context;
	}
	
	public void add(UserData u){
		list.add(u);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RankUserView v;
		if(convertView == null){
			v = new RankUserView(mContext);
		}else{
			v = (RankUserView)convertView;
		}
		v.setRankUserData(list.get(position));
		return v;
	}

}
