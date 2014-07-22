package com.tacademy.penthouse.item;

import java.util.ArrayList;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ItemRecommandAdapter extends BaseAdapter{
	ArrayList<Integer> items = new ArrayList<Integer>();
	Context mContext;
	public ItemRecommandAdapter(Context context){
		mContext = context;
	}
	
	public void add(Integer resId){
		items.add(resId);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Integer getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemRecommandView v;
		if(convertView == null){
			v = new ItemRecommandView(mContext);
		}else{
			v = (ItemRecommandView)convertView;
		}
		v.setItemRecommandData(items.get(position));
		
		return v;
	}

}
