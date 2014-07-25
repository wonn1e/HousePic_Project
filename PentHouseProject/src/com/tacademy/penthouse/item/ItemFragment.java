package com.tacademy.penthouse.item;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;


public class ItemFragment extends Fragment{

	ItemData iData;
	ImageView item_img;
	TextView tv;
	int position;
	int resId;
	boolean isLiked; 
	
	public interface OnShowItemLikeClickedListener{
		public void onShowItemLikeClicked(boolean liked);
	}
	
	OnShowItemLikeClickedListener mListener;
	
	public void setOnShowItemLikeClickedListener(OnShowItemLikeClickedListener l){
		mListener = l;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		resId = b.getInt("img");
		isLiked = b.getBoolean("item_like");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.item_fragment_layout, container,false);
		item_img = (ImageView)v.findViewById(R.id.item_img);
		item_img.setImageResource(resId);
		return v;
		
	}
}
