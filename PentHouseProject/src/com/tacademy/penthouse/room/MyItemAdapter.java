package com.tacademy.penthouse.room;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.room.ItemAdapter.OnAdapterItemClickListener;

public class MyItemAdapter extends BaseAdapter implements MyItemView.OnItemLikeClickListener, MyItemView.OnItemMoveClickListener{

	ArrayList<ItemData> items = new ArrayList<ItemData>();
	Context mContext;
	
	public MyItemAdapter(Context context){
		mContext = context;
	}
	public void add(ItemData data){
		items.add(data);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyItemView v;
		if(convertView == null){
			v = new MyItemView(mContext);
			v.setOnItemLikeClickListener(this);
			v.setOnItemMoveClickListener(this);
		}else{
			v = (MyItemView)convertView;
		}
		v.setItemData(items.get(position));
		
		return v;
	}
	
	public interface OnAdapterItemClickListener{
		public void onItemLikeClick(View v, ItemData iData);
		public void onItemMoveClick(View v, ItemData iData);
	}
	
	OnAdapterItemClickListener mAdapListener;
	
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener l){
		mAdapListener = l;
	}
	
	@Override
	public void onLikeClick(View v, ItemData i) {
		if(mAdapListener != null)
			mAdapListener.onItemLikeClick(v, i);
	}
	@Override
	public void onMoveClick(View v, ItemData i) {
		if(mAdapListener != null)
			mAdapListener.onItemMoveClick(v, i);
	}
	

}
