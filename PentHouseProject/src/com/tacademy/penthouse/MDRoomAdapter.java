package com.tacademy.penthouse;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomsData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.manager.DataManager;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

public class MDRoomAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter ,MDItemView.OnItemDataClickListener, MDItemView.OnItemDataLikeClickListener{
	Context mContext;
//	MultiRoomResult mrr = new MultiRoomResult();
	RoomsData mrr = new RoomsData();
	
	ArrayList<ItemData> items = new ArrayList<ItemData>();

	public MDRoomAdapter(Context context){
		mContext = context;
	}
	
	public void put(RoomsData rr){
		mrr = rr;
		for(int i = 0; i < rr.rooms.size(); i++){
			for(int j = 0; j < rr.rooms.get(i).items.size() ; j++){
				items.add(rr.rooms.get(i).items.get(j));
			
			}
			items.add(new ItemData(rr.rooms.get(i).items.get(0).room_num,0,"","","","","",null,0,0, null,"", false));
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
		RoomData rd = dm.getRoomData(mrr, getItem(position).room_num);
		if(convertView == null){
			v = new MDRoomView(mContext);
		}else{
			v = (MDRoomView)convertView;
		}
		v.setData(rd);

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
