package com.tacademy.penthouse.item;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tacademy.penthouse.entity.ItemData;


public class ItemFragmentAdapter extends FragmentPagerAdapter{
//	ArrayList<ItemData> items = new ArrayList<ItemData>();
	
	ItemData item;
	//int mCount = items.
	public ItemFragmentAdapter(FragmentManager fm, ItemData item) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.item = item;
	}
	
	

	@Override
	public Fragment getItem(int position) {
		Fragment f = new ItemFragment();
		Bundle b = new Bundle();
		b.putInt("img", item.item_img[position]);
		b.putBoolean("item_like", item.item_like);
		f.setArguments(b);
		return f;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return item.item_img.length;
	}

}
