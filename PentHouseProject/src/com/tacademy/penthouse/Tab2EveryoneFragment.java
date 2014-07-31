package com.tacademy.penthouse;

import java.util.ArrayList;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomsResult;
import com.tacademy.penthouse.entity.RoomsData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.RoomItemsResult;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.CreateNewRoomActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.room.MyRoomInfoActivity;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupCollapseListener;

public class Tab2EveryoneFragment extends Fragment {
	
	public static final int REQEUST_NEW_ROOM = 0;
	UserData myData;
	RoomData myRoomData;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	
	ItemLikeShowListDialog itemLikeDialog;
	GridView mdGridView;
	MDRoomAdapter mdAdapter;
	
	/////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		itemLikeDialog = new ItemLikeShowListDialog();
		View v = inflater.inflate(R.layout.tab2_everyone_layout, container, false);
		mdGridView = (GridView)v.findViewById(R.id.every_grid);
		((StickyGridHeadersGridView)mdGridView).setAreHeadersSticky(false);
		mdAdapter = new MDRoomAdapter(getActivity());
		NetworkManager.getInstance().getMDRoomData(getActivity(), new NetworkManager.OnResultListener<RoomsResult>() {

			@Override
			public void onSuccess(RoomsResult result) {

			}

			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "fail to get MD rooms", Toast.LENGTH_SHORT).show();
			}
		});

		mdAdapter.setOnAdapterItemClickListener(new MDRoomAdapter.OnAdapterItemClickListener() {

			@Override
			public void onItemClick(View v, ItemData data) {
				Intent i = new Intent(getActivity(), ItemInfoActivity.class);
				i.putExtra("iData", data);
				startActivity(i);
				
			}
		});

		
		
		
		mdAdapter.setOnAdapterItemLikeClickListener(new MDRoomAdapter.OnAdapterItemLikeClickListener() {

			@Override
			public void onItemLikeClick(View v, ItemData data) {				
				
				//now unlike!!
				if(data.item_like){
					mdAdapter.updateData(data, false, data.likeCnt--);
					onResume();
					
				}
				//now like!!
				else{
					
					//idata update! (ex. likeCnt, etc)
				
					Bundle b = new Bundle();
					b.putParcelable(ItemLikeShowListDialog.PARAM_ITEM_DATA, data);
				//	b.putParcelableArray(ItemLikeShowListDialog.PARAM_ROOM_DATA, myRoomData);
					itemLikeDialog.setArguments(b);
					itemLikeDialog.show(getFragmentManager(), "dialog");
					
					final ItemData itemData = data;
					itemLikeDialog.setOnRoomSelectedListener(new ItemLikeShowListDialog.OnRoomSelectedListener() {
						
						@Override
						public void onRoomSelected(boolean roomSelected) {

							mdAdapter.updateData(itemData, true, itemData.likeCnt++);
						}
					});
					
					itemLikeDialog.setOnCreateSelectedListener(new ItemLikeShowListDialog.OnCreateSelectedListener() {
						
						@Override
						public void onCreateSelected(boolean roomSelected) {
							Intent i = new Intent(getActivity(), CreateNewRoomActivity.class);
							i.putExtra("iData", itemData);
							//pass on myData (UserData) 
							i.putExtra("myData", myData);
							startActivityForResult(i, REQEUST_NEW_ROOM);
							onResume();
							
						}
					});

				}
			}
		});


		mdGridView.setAdapter(mdAdapter);
		initData();

//		mdGridView.setOnGroupClickListener(new GridView.OnGroupClickListener() {
//
//			@Override
//			public boolean onGroupClick(GridView parent, View v, int groupPosition, long id) {
//				Toast.makeText(getActivity(), "this Position : " + groupPosition, Toast.LENGTH_SHORT).show();
//				//my room인지 user룸인지 판단!
//				Intent i = new Intent(getActivity(), MyRoomInfoActivity.class);
//				i.putExtra("iData", rData[groupPosition]);
//				startActivityForResult(i, 0);
//				return false;
//			}
//		});
//Intent (Move activity)


		return v;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQEUST_NEW_ROOM && resultCode == Activity.RESULT_OK){
			
			Toast.makeText(getActivity(), "item in new room back in Tab1", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void initData(){
//		mdAdapter.put(mrr);
	}
}
