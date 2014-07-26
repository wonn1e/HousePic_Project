package com.tacademy.penthouse;

import java.util.ArrayList;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class MDAdapter extends BaseExpandableListAdapter implements MDItemView.OnItemDataClickListener{

	Context mContext;
	ArrayList<RoomData> items = new ArrayList<RoomData>();
	
	public MDAdapter(Context context){
		mContext = context;
	}
	
	public void put(RoomData rd, ItemData id, ItemData id2){
		RoomData item = null;
		for(RoomData data :items){
			if(rd.room_num == data.room_num){
				item = data;
				break;
			}
		}
		if(item == null){
			item = new RoomData();
			item.room_num = rd.room_num;
			items.add(item);
		}
		item.items.add(id);
		item.items.add(id2);
		notifyDataSetChanged();
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		RoomData item = items.get(groupPosition);
		return item.items.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return (((long)groupPosition) << 32 | ((long)childPosition));
	}

	

	@Override
	public int getChildrenCount(int groupPosition) {
		RoomData item = items.get(groupPosition);
		
		return (item.items.size() + 1) / 2;
	}

	@Override
	public Object getGroup(int groupPosition) {
		RoomData item = items.get(groupPosition);
		return item.room_num;
	}

	@Override
	public int getGroupCount() {
		return items.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int getGroupTypeCount() {
		return super.getGroupTypeCount();
	}
	
	@Override
	public int getGroupType(int groupPosition) {
		// TODO Auto-generated method stub
		return super.getGroupType(groupPosition);
	}
	
	@Override
	public int getChildTypeCount() {
		// TODO Auto-generated method stub
		return super.getChildTypeCount();
	}
	
	@Override
	public int getChildType(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return super.getChildType(groupPosition, childPosition);
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		MDRoomView v;
		if(convertView == null){
			v = new MDRoomView(mContext);
		}else{
			v = (MDRoomView)convertView;
		}
		v.setData(items.get(groupPosition));
		return v;
	}
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		MDItemView v;
		
		if(convertView == null){
			v = new MDItemView(mContext);
			v.setOnItemDataClickListener(this);
		}else{
			v = (MDItemView)convertView;
		}		
		if(items.get(groupPosition).items.size() % 2 == 0){
			v.setData(items.get(groupPosition).items.get(childPosition * 2), items.get(groupPosition).items.get(childPosition * 2 + 1));
		}else{
			v.setData(items.get(groupPosition).items.get(childPosition * 2), null);
		}
		
		
		return v;
	}
	
	public interface OnAdapterItemClickListener{
		public void onItemClick(View v, ItemData data);
	}
	OnAdapterItemClickListener mAdapterListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener){
		mAdapterListener = listener;
	}

	@Override
	public void onItemClick(View v, ItemData data) {
		if(mAdapterListener != null){
			mAdapterListener.onItemClick(v, data);
		}
	}
	
	
	

	
}
