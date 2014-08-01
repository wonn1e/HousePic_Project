package com.tacademy.penthouse.room;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.UserRoomsData;

public class ItemAdapter extends BaseAdapter implements ItemView.OnItemLikeClickListener{

	ArrayList<ItemData> items = new ArrayList<ItemData>();
	Context mContext;
	
	public ItemAdapter(Context context){
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
		ItemView v;
		if(convertView == null){
			v = new ItemView(mContext);
			v.setOnItemLikeClickListener(this);
		}else{
			v = (ItemView)convertView;
		}
		v.setItemData(items.get(position));
		
		return v;
	}
	
	public interface OnAdapterItemClickListener{
		public void onItemLikeClick(View v, ItemData iData);
	}
	
	public void updateData(ItemData data, boolean isLike){
		
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

}
