package com.tacademy.penthouse;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.RoomsData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomItemsData;
import com.tacademy.penthouse.manager.DataManager;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

public class UsersRoomAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter , MDItemView.OnItemDataLikeClickListener{

	Context mContext;
	UserRoomItemsData userRoomD = new UserRoomItemsData();
	ArrayList<ItemData> items = new ArrayList<ItemData>();
	
	public UsersRoomAdapter(Context c){
		mContext= c;
	}
	
	public ArrayList<ItemData> set(){
		return items;
	}
	
	public void put(UserRoomItemsData userRoomD){
		this.userRoomD = userRoomD;
		for(int i=0; i<userRoomD.items.size(); i++){
			items.add(userRoomD.items.get(i));
		}
		if(userRoomD.items.size() > 5)
				items.add(new ItemData(userRoomD.items.get(0).room_num, 0, "", "", "", "", "", null, 0, "", 0, null, "", false));
		
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public ItemData getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
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
	public void onItemDataLikeClick(View v, ItemData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getHeaderId(int position) {
		return getItem(position).room_num;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		UserRoomView v;
		DataManager dm = new DataManager();
		RoomData rd = dm.getRoomData(userRoomD, getItem(position).room_num);
		UserData ud = dm.getUserData(userRoomD, userRoomD.user.user_num);
		
		if(convertView == null){
			v = new UserRoomView(mContext);
		}else{
			v = (UserRoomView)convertView;
		}
		v.setData(rd, ud);

		return v;
	}

}
