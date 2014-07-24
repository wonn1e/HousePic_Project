package com.tacademy.penthouse.ranking;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.UserData;


public class RankingActivity extends Activity{
	
	//UserData uData;
	//ItemData iData;
	ListView rankingList;
	RankUserAdapter uAdapter;
	RankItemAdapter iAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.ranking_layout);
		super.onCreate(savedInstanceState);
		
		
		String[] t = {"aa","bb"};
		int[] img = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
		Integer[] img2= {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
		final ItemData iData1= new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img);
		final ItemData[] iData = {new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img), new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img),new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img),new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img),new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img),
						new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img),new ItemData(1,1,"aa","aa","aa","aa","aa",t,1,"aa",img)};
		int uimg = R.drawable.ic_launcher;
		final UserData[] uData = {new UserData(1, "a","a","a",1,1,uimg),
							new UserData(2, "b","b","b",2,2,uimg),
							new UserData(3, "c","c","c",3,3,uimg),
							new UserData(4, "d","d","d",4,4,uimg),
							new UserData(5, "e","e","e",5,5,uimg),
							new UserData(6, "f","f","f",6,6,uimg),
							new UserData(7, "g","g","g",7,7,uimg),
							new UserData(8, "h","h","h",8,8,uimg),
							new UserData(9, "i","i","i",9,9,uimg),
							new UserData(10, "j","j","j",10,10,uimg),
							new UserData(11, "z","z","z",11,11,uimg),
							new UserData(12, "k","k","k",12,12,uimg),
							new UserData(13, "l","l","l",13,13,uimg),
							new UserData(14, "m","m","m",14,14,uimg),
							new UserData(15, "n","n","n",15,15,uimg),
							new UserData(16, "o","o","o",16,16,uimg),
							new UserData(17, "p","p","p",17,17,uimg),
							new UserData(18, "q","q","q",18,18,uimg),
							new UserData(19, "r","r","r",19,19,uimg),
							new UserData(20, "s","s","s",20,20,uimg)
		
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
				rankingList = (ListView)findViewById(R.id.listView_rank);
				rankingList.setAdapter(iAdapter);
				for(int i = 0; i < iData.length; i++){
					iAdapter.add(iData[i]);
				}
				
			}
		});
		
		
		
	}

	/*@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		setContentView(R.layout.ranking_layout);
		
		Button btn = (Button)findViewById(R.id.rank_neighbor_tab);
		btn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				for(int i = 0; i < uData.length; i++){
					uAdapter.add(uData[i]);
				}
			}
		});
		
		btn = (Button)findViewById(R.id.rank_item_tab);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				rankingList = (ListView)findViewById(R.id.listView_rank);
				rankingList.setAdapter(iAdapter);
				
			}
		});
		
		return super.onCreateView(name, context, attrs);
	}*/
}
