package com.tacademy.penthouse.ranking;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ItemsResult;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UsersResult;
import com.tacademy.penthouse.house.HouseActivity;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.CreateNewRoomActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;

public class RankingActivity extends FragmentActivity{
	
	public static int LIST_TYPE_FLAG = 0;
	public static final int REQUEST_NEW_ROOM_IN_RANKING = 0;
	
	ListView rankingList;
	RankUserAdapter uAdapter;
	RankItemAdapter iAdapter;
	UserData[] uData;
	ItemData[] iData;
	ItemLikeShowListDialog itemLikeDialog;
	UserData myData;
	RoomData[] myRoomData;
	
	UsersResult usersResult;
	ItemsResult itemsResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.ranking_layout);
		super.onCreate(savedInstanceState);
		itemLikeDialog = new ItemLikeShowListDialog();

		rankingList = (ListView)findViewById(R.id.listView_rank);
		uAdapter = new RankUserAdapter(this);
		iAdapter = new RankItemAdapter(this);
		
		Button btn = (Button)findViewById(R.id.rank_neighbor_tab);
		
		btn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				LIST_TYPE_FLAG = 0;
				uAdapter.clear();
				
				initUsersData();
			}
		});
		
		btn = (Button)findViewById(R.id.rank_item_tab);
		btn.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				LIST_TYPE_FLAG = 1;
				rankingList = (ListView)findViewById(R.id.listView_rank);
				iAdapter.clear();
				rankingList.setAdapter(iAdapter);
				
				initItemData();
			}
		});
		
		rankingList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(LIST_TYPE_FLAG == 0){		//user
					Intent i = new Intent(RankingActivity.this, HouseActivity.class);
					i.putExtra("uData", uData[position]);
					startActivityForResult(i, 0);
					
				}else if(LIST_TYPE_FLAG == 1){	//item
					Intent i = new Intent(RankingActivity.this, ItemInfoActivity.class);
					//i.putExtra("iData", iData[position]);
					i.putExtra("iData", itemsResult.result.items.get(position).item_num);
					startActivityForResult(i, 0);
				}
			}
		});
		
		iAdapter.setOnAdapterPopularItemLikeListener(new RankItemAdapter.OnAdapterPopularItemLikeListener() {
			
			@Override
			public void onPopularItemLike(View v, ItemData data) {
				//now unlike!!
				if(data.item_like){
					Toast.makeText(RankingActivity.this, "now unlike in RankingActivity", Toast.LENGTH_SHORT).show();
					iAdapter.updateData(data, false, data.likeCnt--);
					onResume();
					
				}
				//now like!!
				else{
					
					//idata update! (ex. likeCnt, etc)
				
					Bundle b = new Bundle();
					b.putParcelable(ItemLikeShowListDialog.PARAM_ITEM_DATA, data);
					b.putParcelableArray(ItemLikeShowListDialog.PARAM_ROOM_DATA, myRoomData);
					itemLikeDialog.setArguments(b);
					itemLikeDialog.show(getSupportFragmentManager(), "dialog");
					
					final ItemData itemData = data;
					itemLikeDialog.setOnRoomSelectedListener(new ItemLikeShowListDialog.OnRoomSelectedListener() {
						
						@Override
						public void onRoomSelected(boolean roomSelected) {
							Toast.makeText(RankingActivity.this, "item in room!! (RankingAct)", Toast.LENGTH_SHORT).show();
							iAdapter.updateData(itemData, true, itemData.likeCnt++);
						}
					});
					
					itemLikeDialog.setOnCreateSelectedListener(new ItemLikeShowListDialog.OnCreateSelectedListener() {
						
						@Override
						public void onCreateSelected(boolean roomSelected) {
							Toast.makeText(RankingActivity.this, "create new room RankingActivity", Toast.LENGTH_SHORT).show();
							Intent i = new Intent(RankingActivity.this , CreateNewRoomActivity.class);
							i.putExtra("iData", itemData);
							//pass on myData (UserData) 
							i.putExtra("myData", myData);
							startActivityForResult(i, REQUEST_NEW_ROOM_IN_RANKING);
							onResume();
							
						}
					});

				}
			}
		});
		
		initUsersData();
	}
	
	private void initUsersData(){
		NetworkManager.getInstance().getUserRankingResultData(RankingActivity.this, new NetworkManager.OnResultListener<UsersResult>() {

			@Override
			public void onSuccess(UsersResult result) {
				usersResult = result;
				uAdapter.put(result.result);
				rankingList.setAdapter(uAdapter);
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(RankingActivity.this, "fail to get users ranking", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void initItemData(){
		NetworkManager.getInstance().getItemRankingResultData(RankingActivity.this, new NetworkManager.OnResultListener<ItemsResult>() {

			@Override
			public void onSuccess(ItemsResult result) {
				itemsResult = result;
				iAdapter.put(result.result);
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(RankingActivity.this, "fail to get items ranking", Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_NEW_ROOM_IN_RANKING && resultCode == Activity.RESULT_OK){
			Toast.makeText(RankingActivity.this, "item in room from ranking!", Toast.LENGTH_SHORT).show();
		}
	}
}
