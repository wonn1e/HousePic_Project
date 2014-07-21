package com.tacademy.penthouse.ranking;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.house.RankItemView;

public class RankItemAdapter extends BaseAdapter {
	ArrayList<ItemData> list = new ArrayList<ItemData>();
	Context mContext;
	public RankItemAdapter(Context context){
		mContext = context;
	}
	
	public void add(ItemData d){
		list.add(d);
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
		RankItemView v;
		if(convertView == null){
			v = new RankItemView(mContext);
		}else{
			v = (RankItemView)convertView;
		}
		v.setRankItemData(list.get(position));
		return v;
	}

}
