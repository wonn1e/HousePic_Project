package com.tacademy.penthouse.house;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.MDItemView;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomsData;
import com.tacademy.penthouse.manager.DataManager;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

public class RoomAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter ,MDItemView.OnItemDataClickListener, MDItemView.OnItemDataLikeClickListener{
	Context mContext;
	
	UserRoomsData urData = new UserRoomsData();	
	ArrayList<RoomData> rooms = new ArrayList<RoomData>();

	public RoomAdapter(Context context){
		mContext = context;
	}
	
	
	public void put(UserRoomsData rD){
		urData = rD;
		for(int i= 0; i<rD.rooms.size(); i++){
			rooms.add(rD.rooms.get(i));
		}
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return rooms.size();
	}

	@Override
	public RoomData getItem(int arg0) {	
		return rooms.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		
		return arg0;
	}

	@Override
	public long getHeaderId(int position) {
		
		return getItem(position).user_num;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RoomInHouseView v;
		if(convertView == null){
			v = new RoomInHouseView(mContext);
		}else{
			v = (RoomInHouseView)convertView;
		}
		v.setHouseRoomData(rooms.get(position));
		return v;
	
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		HouseView v;
		DataManager dm = new DataManager();
		UserData ud = dm.getUserData(urData, getItem(position).user_num);
		if(convertView == null){
			v = new HouseView(mContext);
		}else{
			v = (HouseView)convertView;
		}
		v.setData(ud);

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
