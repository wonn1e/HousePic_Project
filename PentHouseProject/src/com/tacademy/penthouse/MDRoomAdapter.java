package com.tacademy.penthouse;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.MultiUserRoomItemsData;
import com.tacademy.penthouse.entity.RoomsData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomItemsData;
import com.tacademy.penthouse.manager.DataManager;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

public class MDRoomAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter ,MDItemView.OnItemDataClickListener, MDItemView.OnItemDataLikeClickListener{
	Context mContext;
	MultiUserRoomItemsData userRoomItems = new MultiUserRoomItemsData();
	
	ArrayList<ItemData> items = new ArrayList<ItemData>();

	public MDRoomAdapter(Context context){
		mContext = context;
	}
	
	public ArrayList<ItemData> set(){
		return items;
	}
	
	public void put(MultiUserRoomItemsData rr){
		userRoomItems = rr;
		for(int j = 0; j < userRoomItems.users.size(); j++){
				
			for(int i=0; i<userRoomItems.users.get(j).items.size(); i++){
				items.add(userRoomItems.users.get(j).items.get(i));
				if(i == 4){
					items.add(new ItemData(userRoomItems.users.get(j).items.get(0).room_num, 0, "", "", "", "", "", null, 0, "", 0, null, "", false));
					break;
				}
			}	

		}
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public ItemData getItem(int arg0) {	
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		
		return arg0;
	}

	@Override
	public long getHeaderId(int position) {
		
		return getItem(position).room_num;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MDItemView v;
		
		if(convertView == null){
			v = new MDItemView(mContext);
			v.setOnItemDataLikeClickListener(this);
		}else{
			v = (MDItemView)convertView;
		}
		
		v.setData(items.get(position));
		
		return v;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		MDRoomView v;
		DataManager dm = new DataManager();
		RoomData rd = userRoomItems.users.get(position).room;//dm.getRoomData(userRoomItems.users.get(position).room, getItem(position).room_num);
		UserData ud = userRoomItems.users.get(position).user; //dm.getUserData(userRoomItems.users, userRoomItems.users.get(position).user.user_num);
		if(convertView == null){
			v = new MDRoomView(mContext);
		}else{
			v = (MDRoomView)convertView;
		}
		v.setData(rd, ud);

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
	
	
	
	public interface OnAdapterItemLikeClickListener{
		public void onItemLikeClick(View v, ItemData data);
	}
	
	OnAdapterItemLikeClickListener lAdapterListener;
	
	public void setOnAdapterItemLikeClickListener(OnAdapterItemLikeClickListener listener){
		lAdapterListener = listener;
	}
	
	public void updateData(ItemData data, boolean isLike, int likeCnt) {
		data.item_like = isLike;
		data.likeCnt = likeCnt;
		notifyDataSetChanged();
	}
	
	@Override
	public void onItemDataLikeClick(View v, ItemData data) {
		if(lAdapterListener != null){
			lAdapterListener.onItemLikeClick(v, data);
		}
	}
	
	
}
