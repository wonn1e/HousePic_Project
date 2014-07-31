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
import com.tacademy.penthouse.room.UserRoomInfoActivity;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView.OnHeaderClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;

public class Tab3FriendsFragment extends Fragment implements OnItemClickListener,
OnHeaderClickListener{

	public static final int REQEUST_NEW_ROOM = 0;
	RoomsResult myrr = new RoomsResult();
	UserData myData;
	String u_id = myData.user_id;
	RoomData myRoomData;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	ItemLikeShowListDialog itemLikeDialog;
	GridView mdGridView;
	MDRoomAdapter mdAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		itemLikeDialog = new ItemLikeShowListDialog();
		View v = inflater.inflate(R.layout.tab3_friends_layout, container, false);
		//mdGridView = (GridView)v.findViewById(R.id.md_grid);
		mdGridView = (GridView)v.findViewById(R.id.friends_grid);
		mdGridView.setOnItemClickListener(this);
		((StickyGridHeadersGridView)mdGridView).setOnHeaderClickListener(this);
		((StickyGridHeadersGridView)mdGridView).setAreHeadersSticky(false);
		mdAdapter = new MDRoomAdapter(getActivity());
		NetworkManager.getInstance().getMDRoomData(getActivity(), new NetworkManager.OnResultListener<RoomsResult>() {

			@Override
			public void onSuccess(RoomsResult result) {
				myrr = result;
				mdAdapter.put(result.result);
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "fail to get MD rooms", Toast.LENGTH_SHORT).show();
			}
		});

		mdAdapter.setOnAdapterItemClickListener(new MDRoomAdapter.OnAdapterItemClickListener() {

			@Override
			public void onItemClick(View v, ItemData data) {
				//DataAdapter를 만들어놓음~ 지금은 안씀 필요하면 쓰기
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
		//mdAdapter.put(mrr);
	}

	@Override
	public void onHeaderClick(AdapterView<?> parent, View view, long id) {
		Intent i = new Intent(getActivity(), UserRoomInfoActivity.class);
		i.putExtra("r_num", myrr.result.rooms.get((int)id).room.room_num);
		//i.putExtra("rData", myMRR.result.rooms.get((int)id).room);
		//i.putExtra("iData", myMRR.result.rooms.get((int)id).items);
		startActivity(i);
	}

	@Override
	public void onItemClick(AdapterView<?> gridView, View view, int position, long id) {
		Intent i = new Intent(getActivity(), ItemInfoActivity.class);
		i.putExtra("iData",myrr.result.rooms.get((int)mdAdapter.getHeaderId(position)).items.get(position) );
		startActivity(i);		
	}
}
