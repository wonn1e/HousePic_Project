package com.tacademy.penthouse.item;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemData;

public class ItemInfoAdapter extends BaseAdapter{

	ArrayList<ItemData> items = new ArrayList<ItemData>();
	Context mContext;
	public ItemInfoAdapter(Context context){
		mContext = context;
	}
	public void add(ItemData data){
		items.add(data);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public ItemData getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemInfoView v;
		if(convertView == null){
			v = new ItemInfoView(mContext);	
		}else{
			v = (ItemInfoView)convertView;
		}
		v.setItemImageData(items.get(position));
		return v;
	}

}
