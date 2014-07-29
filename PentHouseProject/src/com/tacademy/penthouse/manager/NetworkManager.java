package com.tacademy.penthouse.manager;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.tacademy.penthouse.MyApplication;

public class NetworkManager {

	private static NetworkManager instance;
	AsyncHttpClient client;
	Gson gson;
	
	Handler mHandler;
	public static NetworkManager getInstance(){
		
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}
	private NetworkManager() {
		mHandler = new Handler(Looper.getMainLooper());
		client = new AsyncHttpClient();
		client.setCookieStore(new PersistentCookieStore(MyApplication.getContext()));
	//	client.setTimeout(30000);
		gson = new Gson();
	}
	
	public interface OnResultListener<T>{
		public void onSuccess(T result);
		public void onFail(int code);
	}
	
	public static final String USER_DATA_URL = "서버에서 받을 주소";
	
	public void getUserData(){
		
	}
	public static final String HOUSE_DATA_URL = "서버에서 받을 주소";
	public void getHouseData(){
		
	}
	public static final String ROOM_DATA_URL = "서버에서 받을 주소";
	public void getRoomData(){
		
	}
	public static final String ITEM_DATA_URL = "서버에서 받을 주소";
	public void getItemData(){
		
	}
	
	//static class DataTask extends AsyncTask...
	
}
