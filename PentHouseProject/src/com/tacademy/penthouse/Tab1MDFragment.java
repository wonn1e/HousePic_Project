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
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.CreateNewRoomActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.room.MyRoomInfoActivity;

public class Tab1MDFragment extends Fragment {
	
	public static final int REQEUST_NEW_ROOM = 0;
	UserData myData;
	ItemData[] iData;
	RoomData[] rData;
	RoomData[] myRoomData;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	
	ItemLikeShowListDialog itemLikeDialog;
	ExpandableListView mdListView;
	MDRoomAdapter mdAdapter;
//	String[] t = {"aa","bb"};
//	int[] img = {R.drawable.tulips,R.drawable.tulips,R.drawable.tulips,R.drawable.tulips,R.drawable.tulips};
//	int[] recmd_img = {R.drawable.tulips,R.drawable.tulips,R.drawable.tulips,R.drawable.tulips,R.drawable.tulips};
//	
//	
//	ItemData[] iData = {new ItemData(1,1,"aa","i1","aa","aa","aa",t,1,"aa",img,"http://www.naver.com", true),
//			new ItemData(2,2,"bb","i2","fdsf","dsf","fd",t,1,"dfs",img,"http://www.naver.com", true),
//			new ItemData(3,3,"12321","i3","1213","1231","12321",t,1,"12312",img,"http://www.naver.com", false),
//			new ItemData(4,4,"dd","i4","dddd","ddd","ddd",t,1,"dddd",img,"http://www.naver.com", false),
//			new ItemData(5,5,"eeeee","i5","eeee","eee","ee",t,1,"ee",img,"http://www.naver.com", false),
//			new ItemData(6,6,"zxxzxc","i6","zcxczx","zczcx","zcxcxz",t,1,"zcxx",img,"http://www.naver.com", true),
//			new ItemData(7,7,"jhjh","i7","hjhj","hjhj","hjhj",t,1,"hjhj",img,"http://www.naver.com", true),
//			new ItemData(8,8,"jhjh","i8","hjhj","hjhj","hjhj",t,1,"hjhj",img,"http://www.naver.com", true),
//			new ItemData(9,9,"jhjh","i9","hjhj","hjhj","hjhj",t,1,"hjhj",img,"http://www.naver.com", false),
//			new ItemData(10,10,"jhjh","i10","hjhj","hjhj","hjhj",t,1,"hjhj",img,"http://www.naver.com", true),
//			new ItemData(11,11,"jhjh","i11","hjhj","hjhj","hjhj",t,1,"hjhj",img,"http://www.naver.com", false)
//	};
//	RoomData[] rData = {
//			new RoomData(1,1,"house1",R.drawable.tulips,"방설명1",true),
//			new RoomData(2,2,"house2",R.drawable.penguins,"방설명2",true),
//			new RoomData(3,3,"house3",R.drawable.tulips,"방설명3",true)
//	};
//	final RoomData[] myRoomData = {
//			new RoomData(1,1,"house1",R.drawable.penguins,"방설명1",true),
//			new RoomData(2,2,"house2",R.drawable.penguins,"방설명2",true),
//			new RoomData(3,3,"house3",R.drawable.penguins,"방설명3",true)
//	};

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		itemLikeDialog = new ItemLikeShowListDialog();
		
		View v = inflater.inflate(R.layout.tab1_md_layout, container, false);
		mdListView = (ExpandableListView)v.findViewById(R.id.md_list);
		mdAdapter = new MDRoomAdapter(getActivity());

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
