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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;

public class ItemLikeShowListDialog extends DialogFragment {

	ListView room_list;
	RoomData[] rData;
	ItemData iData;
	RoomNameAdapter rAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.HouseDialog);

		//item data & room data �޾ƿ���
		Bundle iB = getArguments();
		if(iB != null){
			iData = (ItemData) iB.getParcelable("iData");
			rData = (RoomData[]) iB.getParcelableArray("rData");
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
					Toast.makeText(parent.getContext(), "item in room "+ rData[position].room_name, Toast.LENGTH_SHORT).show();
				}
				
				dismiss();
			}
		});
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		Dialog d = getDialog();
		d.setTitle("�� ���");
		d.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
	}
}
