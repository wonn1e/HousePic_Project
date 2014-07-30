package com.tacademy.penthouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.Toast;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.MultiRoomResult;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.RoomInfoResult;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.CreateNewRoomActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.room.MyRoomInfoActivity;

public class Tab1MDFragment extends Fragment {
	
	public static final int REQEUST_NEW_ROOM = 0;
	UserData myData;
//	ItemData[] iData;
//	RoomData[] rData;
//	RoomData[] myRoomData;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	
	ItemLikeShowListDialog itemLikeDialog;
	ExpandableListView mdListView;
	MDRoomAdapter mdAdapter;
	
	/////////////////////////
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
	
	
	ItemData[] iData = {new ItemData(1,1,"aa","침대1","aa","aa","aa",t,1,0,img,"http://www.naver.com", true),
			new ItemData(2,2,"bb","i2","fdsf","dsf","fd",t,1,0,img,"http://www.naver.com", true),
			new ItemData(3,3,"12321","i3","1213","1231","12321",t,1,2,img,"http://www.naver.com", false),
			new ItemData(4,4,"dd","i4","dddd","ddd","ddd",t,1,1,img,"http://www.naver.com", false),
			new ItemData(5,5,"eeeee","i5","eeee","eee","ee",t,1,2, img,"http://www.naver.com", false)
			//new ItemData(null)
			
	};
	RoomData[] rData = {
			new RoomData(1,1,"user1 room1", img[1], "room1",true, "red"),
			new RoomData(2,2,"user2 room1", img[0], "room2",true, "red"),
			new RoomData(3,3,"user1 room3", img[3], "room3",true, "red"),
	};
	final RoomData[] myRoomData = {
			new RoomData(1,1,"house1", img[1], "room1",true, "red"),
			new RoomData(1,3,"user1 room3", img[3], "room3",true, "red")
	};
	//////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		itemLikeDialog = new ItemLikeShowListDialog();
		View v = inflater.inflate(R.layout.tab1_md_layout, container, false);
		mdListView = (ExpandableListView)v.findViewById(R.id.md_list);
		mdAdapter = new MDRoomAdapter(getActivity());
		NetworkManager.getInstance().getMDRoomData(getActivity(), new NetworkManager.OnResultListener<MultiRoomResult>() {

			@Override
			public void onSuccess(MultiRoomResult result) {
				for(RoomInfoResult r: result.rooms){
					RoomData room = r.room;
					ItemData i1 = r.items.get(0);
					ItemData i2 = r.items.get(1);
					ItemData i3 = r.items.get(2);
					ItemData i4 = r.items.get(3);
					ItemData i5 = r.items.get(4);
					
					mdAdapter.put(room, i1, i2);
					mdAdapter.put(room, i3, i4);
					mdAdapter.put(room, i5, null);
				}
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
					b.putParcelableArray(ItemLikeShowListDialog.PARAM_ROOM_DATA, myRoomData);
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


		mdListView.setAdapter(mdAdapter);
		initData();
		for(int i=0; i<mdAdapter.getGroupCount(); i++){
			mdListView.expandGroup(i);
		}
		mdListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				mdListView.expandGroup(groupPosition);

			}
		});

		mdListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				Toast.makeText(getActivity(), "this Position : " + groupPosition, Toast.LENGTH_SHORT).show();
				//my room인지 user룸인지 판단!
				Intent i = new Intent(getActivity(), MyRoomInfoActivity.class);
				i.putExtra("iData", rData[groupPosition]);
				startActivityForResult(i, 0);
				return false;
			}
		});


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
		for(int i = 0; i < rData.length; i++){
			int j = 0;
			while (j < iData.length){

				//mAdapter.put( rData[i], iData[j],iData[j+1] );
				if(iData.length - j != 1){
					mdAdapter.put( rData[i], iData[j],iData[j+1] );
					if(iData.length - j != 1){
						j = j + 2;
					}else{
						j++;
					}

				}else{
					mdAdapter.put( rData[i], iData[j],null);
					j++;
				}				
			}
		}
	}
}
