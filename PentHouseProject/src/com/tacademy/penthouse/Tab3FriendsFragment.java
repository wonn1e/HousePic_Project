package com.tacademy.penthouse;

import java.util.ArrayList;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.MultiRoomResult;
import com.tacademy.penthouse.entity.ResultRooms;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.RoomInfoResult;
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

public class Tab3FriendsFragment  extends Fragment {
	
	public static final int REQEUST_NEW_ROOM = 0;
	UserData myData;
	RoomData myRoomData;
	//DUMMYDATA///////////////////////////////////////////////////////////
	String[] t = {"aa","bb"};
	String[] img = {"http://54.178.158.103:80/room1.jpg",
			"http://tv02.search.naver.net/ugc?t=r180&q=http://blogfiles.naver.net/20140506_64/hellossophia_1399379324258PGAlK_PNG/1399125535162.png",
			"http://tv01.search.naver.net/ugc?q=http://blogfiles.naver.net/20140514_231/jinsoodjdj_1400004009267ylFQB_JPEG/%C8%AD%BA%B8%C0%CC%C1%F8%BF%ED_2.jpg",
					"http://tv01.search.naver.net/ugc?t=r180&q=http://cafefiles.naver.net/20140714_1/leeyuri9487_1405292637586N7eKA_JPEG/news1309847266_253060_1_m.jpg",
					"http://tv02.search.naver.net/ugc?t=r180&q=http://blogfiles.naver.net/20140506_64/hellossophia_1399379324258PGAlK_PNG/1399125535162.png",
					"http://tv02.search.naver.net/ugc?t=r180&q=http://blogfiles.naver.net/20140610_57/mmro13_1402385652345TSTEm_JPEG/%C0%CC%C1%F8%BF%ED_%287%29.jpg"};
	String[] recmd_img = {"http://tv01.search.naver.net/ugc?q=http://blogfiles.naver.net/20140514_231/jinsoodjdj_1400004009267ylFQB_JPEG/%C8%AD%BA%B8%C0%CC%C1%F8%BF%ED_2.jpg",
			"http://tv01.search.naver.net/ugc?t=r180&q=http://cafefiles.naver.net/20140714_1/leeyuri9487_1405292637586N7eKA_JPEG/news1309847266_253060_1_m.jpg",
			"http://tv02.search.naver.net/ugc?t=r180&q=http://blogfiles.naver.net/20140506_64/hellossophia_1399379324258PGAlK_PNG/1399125535162.png",
			"http://tv02.search.naver.net/ugc?t=r180&q=http://blogfiles.naver.net/20140610_57/mmro13_1402385652345TSTEm_JPEG/%C0%CC%C1%F8%BF%ED_%287%29.jpg"};
	
	RoomData[] rData = {
			new RoomData(1,1,"user1 room1", img[1], "room1",true, "red"),
			new RoomData(2,2,"user2 room1", img[0], "room2",true, "red"),
			new RoomData(3,3,"user1 room3", img[3], "room3",true, "red"),
	};
	
	
	ItemData id1 = new ItemData(1,1,"aa","침대1","aa","aa","aa",t,1,0,img,"http://www.naver.com", true);
	ItemData id2 = new ItemData(1,2,"bb","침대2","fdsf","dsf","fd",t,1,0,img,"http://www.naver.com", true);
	ItemData id3 = new ItemData(1,3,"12321","침대3","1213","1231","12321",t,1,2,img,"http://www.naver.com", false);
	ItemData id4 = new ItemData(1,4,"dd","침대4","dddd","ddd","ddd",t,1,1,img,"http://www.naver.com", false);
	ItemData id5 = new ItemData(1,5,"eeeee","침대5","eeee","eee","ee",t,1,2, img,"http://www.naver.com", false);
	ItemData id6 = new ItemData(2,1,"aa","침대6","aa","aa","aa",t,1,0,img,"http://www.naver.com", true);
	ItemData id7 = new ItemData(2,2,"bb","침대7","fdsf","dsf","fd",t,1,0,img,"http://www.naver.com", true);
	ItemData id8 = new ItemData(2,3,"12321","침대8","1213","1231","12321",t,1,2,img,"http://www.naver.com", false);
	ItemData id9 = new ItemData(2,4,"dd","침대9","dddd","ddd","ddd",t,1,1,img,"http://www.naver.com", false);
	ItemData id0 = new ItemData(2,5,"eeeee","침대10","eeee","eee","ee",t,1,2, img,"http://www.naver.com", false);
	ItemData id11 = new ItemData(3,1,"eeeee","침대11","eeee","eee","ee",t,1,2, img,"http://www.naver.com", false);
	ItemData id12 = new ItemData(3,2,"eeeee","침대12","eeee","eee","ee",t,1,2, img,"http://www.naver.com", false);
	ItemData id13 = new ItemData(3,3,"eeeee","침대13","eeee","eee","ee",t,1,2, img,"http://www.naver.com", false);
	ItemData id14 = new ItemData(3,4,"eeeee","침대14","eeee","eee","ee",t,1,2, img,"http://www.naver.com", false);
	ItemData id15 = new ItemData(3,5,"eeeee","침대15","eeee","eee","ee",t,1,2, img,"http://www.naver.com", false);
	
	
	
	ArrayList<ItemData> a_id1 = new ArrayList<ItemData>();
	{
		a_id1.add(id1);
		a_id1.add(id2);
		a_id1.add(id3);
		a_id1.add(id4);
		a_id1.add(id5);
		
	}
	ArrayList<ItemData> a_id2 = new ArrayList<ItemData>();
	{
		a_id2.add(id6);
		a_id2.add(id7);
		a_id2.add(id8);
		a_id2.add(id9);
		a_id2.add(id0);
	}
	ArrayList<ItemData> a_id3 = new ArrayList<ItemData>();
	{
		a_id3.add(id11);
		a_id3.add(id12);
		a_id3.add(id13);
		a_id3.add(id14);
		a_id3.add(id15);
		
	}
	RoomInfoResult rir1 = new RoomInfoResult(rData[0], a_id1, "aa", "aa");
	RoomInfoResult rir2 = new RoomInfoResult(rData[1], a_id2, "bb", "bb");
	RoomInfoResult rir3 = new RoomInfoResult(rData[2], a_id3, "cc", "cc");
	ArrayList<RoomInfoResult> a_rir = new ArrayList<RoomInfoResult>();
	{
		a_rir.add(rir1);
		a_rir.add(rir2);
		a_rir.add(rir3);
	}
	ResultRooms rr = new ResultRooms(a_rir);
	MultiRoomResult mrr = new MultiRoomResult(rr);
	
	///////////////////////////////////////////////////////////////////////////////////
	
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
		View v = inflater.inflate(R.layout.tab3_friends_layout, container, false);
		mdGridView = (GridView)v.findViewById(R.id.friends_grid);
		((StickyGridHeadersGridView)mdGridView).setAreHeadersSticky(false);
		mdAdapter = new MDRoomAdapter(getActivity());
		NetworkManager.getInstance().getMDRoomData(getActivity(), new NetworkManager.OnResultListener<MultiRoomResult>() {

			@Override
			public void onSuccess(MultiRoomResult result) {

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
		//mdAdapter.put(mrr);
	}
}
