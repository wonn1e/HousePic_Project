package com.tacademy.penthouse.item;

import java.util.ArrayList;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ItemRecommandAdapter extends BaseAdapter{
	ArrayList<String> items = new ArrayList<String>();
	Context mContext;
	public ItemRecommandAdapter(Context context){
		mContext = context;
	}
	
	public void add(String imageURL){
		items.add(imageURL);
		notifyDataSetChanged();
	}
	public void clear(){
		items.clear();
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public String getItem(int position) {
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
