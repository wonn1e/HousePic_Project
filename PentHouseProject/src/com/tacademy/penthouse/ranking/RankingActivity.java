package com.tacademy.penthouse.ranking;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.house.HouseActivity;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.room.MyRoomInfoActivity;


public class RankingActivity extends Activity{
	
	public static int LIST_TYPE_FLAG = 0;
	
	ListView rankingList;
	RankUserAdapter uAdapter;
	RankItemAdapter iAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.ranking_layout);
		super.onCreate(savedInstanceState);
		
		
		String[] t = {"aa","bb"};
		int[] img = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
//		Integer[] img2= {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
		final ItemData[] iData = {new ItemData(1,1,"aa","i1","aa","aa","aa",t,1,"aa",img, "http://www.naver.com", true),
								new ItemData(2,2,"bb","i2","fdsf","dsf","fd",t,1,"dfs",img, "http://www.naver.com", false),
								new ItemData(3,3,"12321","i3","1213","1231","12321",t,1,"12312",img, "http://www.naver.com", false),
								new ItemData(4,4,"dd","i4","dddd","ddd","ddd",t,1,"dddd",img, "http://www.naver.com", false),
								new ItemData(5,5,"eeeee","i5","eeee","eee","ee",t,1,"ee",img, "http://www.naver.com", true),
								new ItemData(6,6,"zxxzxc","i6","zcxczx","zczcx","zcxcxz",t,1,"zcxx",img, "http://www.naver.com", true),
								new ItemData(7,7,"jhjh","i7","hjhj","hjhj","hjhj",t,1,"hjhj",img, "http://www.naver.com", false),
								new ItemData(7,7,"jhjh","i8","hjhj","hjhj","hjhj",t,1,"hjhj",img, "http://www.naver.com", true)
		};
		int uimg = R.drawable.ic_launcher;
		final UserData[] uData = {new UserData(1, "a","a","a",1,1,uimg,"YYY1's House","Welcome","bb"),
							new UserData(2, "b","b","b",2,2,uimg,"YYY1's House","Welcome","bb"),
							new UserData(3, "c","c","c",3,3,uimg,"YYY1's House","Welcome","bb"),
							new UserData(4, "d","d","d",4,4,uimg,"YYY1's House","Welcome","bb"),
							new UserData(5, "e","e","e",5,5,uimg,"YYY1's House","Welcome","bb"),
							new UserData(6, "f","f","f",6,6,uimg,"YYY1's House","Welcome","bb"),
							new UserData(7, "g","g","g",7,7,uimg,"YYY1's House","Welcome","bb"),
							new UserData(8, "h","h","h",8,8,uimg,"YYY1's House","Welcome","bb"),
							new UserData(9, "i","i","i",9,9,uimg,"YYY1's House","Welcome","bb"),
							new UserData(10, "j","j","j",10,10,uimg,"YYY1's House","Welcome","bb"),
							new UserData(11, "z","z","z",11,11,uimg,"YYY1's House","Welcome","bb"),
							new UserData(12, "k","k","k",12,12,uimg,"YYY1's House","Welcome","bb"),
							new UserData(13, "l","l","l",13,13,uimg,"YYY1's House","Welcome","bb"),
							new UserData(14, "m","m","m",14,14,uimg,"YYY1's House","Welcome","bb"),
							new UserData(15, "n","n","n",15,15,uimg,"YYY1's House","Welcome","bb"),
							new UserData(16, "o","o","o",16,16,uimg,"YYY1's House","Welcome","bb"),
							new UserData(17, "p","p","p",17,17,uimg,"YYY1's House","Welcome","bb"),
							new UserData(18, "q","q","q",18,18,uimg,"YYY1's House","Welcome","bb"),
							new UserData(19, "r","r","r",19,19,uimg,"YYY1's House","Welcome","bb"),
							new UserData(20, "s","s","s",20,20,uimg,"YYY1's House","Welcome","bb")
		
		};
		
		
		rankingList = (ListView)findViewById(R.id.listView_rank);
		uAdapter = new RankUserAdapter(this);
		iAdapter = new RankItemAdapter(this);
		rankingList.setAdapter(uAdapter);
		Button btn = (Button)findViewById(R.id.rank_neighbor_tab);
		for(int i = 0; i < uData.length; i++){
			uAdapter.add(uData[i]);
		}
		
		btn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				LIST_TYPE_FLAG = 0;
				rankingList.setAdapter(uAdapter);
				for(int i = 0; i < uData.length; i++){
					uAdapter.add(uData[i]);
				}
			}
		});
		
		btn = (Button)findViewById(R.id.rank_item_tab);
		btn.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				LIST_TYPE_FLAG = 1;
				rankingList = (ListView)findViewById(R.id.listView_rank);
				rankingList.setAdapter(iAdapter);
				for(int i = 0; i < iData.length; i++){
					iAdapter.add(iData[i]);
				}
				
			}
		});
		
		rankingList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(LIST_TYPE_FLAG == 0){
					Intent i = new Intent(RankingActivity.this, HouseActivity.class);
					i.putExtra("uData", uData[position]);
					startActivityForResult(i, 0);
				}else if(LIST_TYPE_FLAG == 1){
					Intent i = new Intent(RankingActivity.this, ItemInfoActivity.class);
					i.putExtra("iData", iData[position]);
					startActivityForResult(i, 0);
				}
			}
		});
		
	}
}
