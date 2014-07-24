package com.tacademy.penthouse;


import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Tab1MDFragment extends Fragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	ExpandableListView mdListView;
	MDAdapter mdAdapter;
	String[] t = {"aa","bb"};
	int[] img = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
	ItemData[] iData = {new ItemData(1,1,"aa","i1","aa","aa","aa",t,1,"aa",img,"http://www.naver.com"),
			new ItemData(2,2,"bb","i2","fdsf","dsf","fd",t,1,"dfs",img,"http://www.naver.com"),
			new ItemData(3,3,"12321","i3","1213","1231","12321",t,1,"12312",img,"http://www.naver.com"),
			new ItemData(4,4,"dd","i4","dddd","ddd","ddd",t,1,"dddd",img,"http://www.naver.com"),
			new ItemData(5,5,"eeeee","i5","eeee","eee","ee",t,1,"ee",img,"http://www.naver.com"),
			new ItemData(6,6,"zxxzxc","i6","zcxczx","zczcx","zcxcxz",t,1,"zcxx",img,"http://www.naver.com"),
			new ItemData(7,7,"jhjh","i7","hjhj","hjhj","hjhj",t,1,"hjhj",img,"http://www.naver.com"),
			new ItemData(8,8,"jhjh","i8","hjhj","hjhj","hjhj",t,1,"hjhj",img,"http://www.naver.com"),
			new ItemData(9,9,"jhjh","i9","hjhj","hjhj","hjhj",t,1,"hjhj",img,"http://www.naver.com"),
			new ItemData(10,10,"jhjh","i10","hjhj","hjhj","hjhj",t,1,"hjhj",img,"http://www.naver.com"),
			new ItemData(11,11,"jhjh","i11","hjhj","hjhj","hjhj",t,1,"hjhj",img,"http://www.naver.com")
	};
	RoomData[] rData = {
			new RoomData(1,1,"house1",R.drawable.ic_launcher,"방설명1",true),
			new RoomData(2,2,"house2",R.drawable.ic_launcher,"방설명2",true),
			new RoomData(3,3,"house3",R.drawable.ic_launcher,"방설명3",true)
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab1_md_layout, container, false);
		mdListView = (ExpandableListView)v.findViewById(R.id.md_list);
		mdAdapter = new MDAdapter(getActivity());
		//listView.addHeaderView(v);
		mdListView.setAdapter(mdAdapter);
		initData();
		return v;
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
