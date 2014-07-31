package com.tacademy.penthouse.ranking;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemsData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UsersData;

public class RankUserAdapter extends BaseAdapter {

	public final static int VISIBLE_FLAG = 1;
	public final static int GONE_FLAG = 0;
	ArrayList<UserData> list = new ArrayList<UserData>();
	ArrayList<Integer> ranker = new ArrayList<Integer>();
	Context mContext;
	UsersData uData;
	
	public RankUserAdapter(Context context){
		mContext = context;
	}
	
	/*public void add(UserData u){
		list.add(u);
		notifyDataSetChanged();
	}
	*/
	
	public void put(UsersData uD){
		uData = uD;
		for(int i = 0; i<uD.users.size(); i++){
			list.add(uD.users.get(i));
		}
		notifyDataSetChanged();
	}
	
	public void clear() {
		list.clear();
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
		v.setRankUserData(list.get(position), position);
		return v;
	}

}
