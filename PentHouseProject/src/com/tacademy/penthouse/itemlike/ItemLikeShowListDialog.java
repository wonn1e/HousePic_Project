package com.tacademy.penthouse.itemlike;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.item.ItemInfoActivity;

public class ItemLikeShowListDialog extends DialogFragment {
	public static final String PARAM_ITEM_DATA = "item data";
	public static final String PARAM_ROOM_DATA = "room data";

	ListView room_list;
	RoomData[] rData;
	ItemData iData;
	RoomNameAdapter rAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.HouseDialog);

		//item data & room data 받아오기
		Bundle iB = getArguments();
		if(iB != null){
			iData = (ItemData) iB.getParcelable(PARAM_ITEM_DATA);
			rData = (RoomData[]) iB.getParcelableArray(PARAM_ROOM_DATA);
		}
		//
		rAdapter = new RoomNameAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog d = getDialog();
		d.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		View v= inflater.inflate(R.layout.item_like_choose_room, container, false);
		room_list = (ListView)v.findViewById(R.id.room_list);

		for(int i=0; i<rData.length; i++){
			if(rData != null){
				rAdapter.add(rData[i]);
			}
		}
		room_list.setAdapter(rAdapter);
		room_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(!iData.item_like){
					iData.item_like = true;
					iData.likeCnt++;
					rData[position].items.add(iData);
				}
				
				dismiss();
			}
		});
		
		Button btn = (Button)v.findViewById(R.id.create_room_btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), CreateNewRoomDialog.class);
				startActivity(i);
			}
		});
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		Dialog d = getDialog();
		d.setTitle("방 목록");
		d.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
	}
}
