package com.tacademy.penthouse.slidingmenu;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ConfigInformAdapter extends BaseAdapter{
	ArrayList<String> items = new ArrayList<String>();
	Context mContext;
	public ConfigInformAdapter(Context context){
		mContext = context;
	}
	public void add(String str){
		items.add(str);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		if(items.get(position) != ""){
			ConfigInformView cv;
				cv = new ConfigInformView(mContext);
			String str = items.get(position);
			cv.setData(str);
			return cv;
	}
		else{
			AlertOnOffView av;
				av = new AlertOnOffView(mContext);
			return av;
		}
	
	}
	
	

}
