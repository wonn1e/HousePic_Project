package com.tacademy.penthouse.ranking;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.tacademy.penthouse.R;

public class RankingActivity extends Activity{
	
	ListView rankingList;
	RankUserAdapter pAdapter;
	RankItemAdapter iAdapter;
	
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		setContentView(R.layout.ranking_layout);
		
		Button btn = (Button)findViewById(R.id.rank_neighbor_tab);
		btn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				rankingList = (ListView)findViewById(R.id.listView_rank);
				rankingList.setAdapter(pAdapter);
				
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
	}
}
