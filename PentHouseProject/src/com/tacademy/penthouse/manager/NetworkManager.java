package com.tacademy.penthouse.manager;

import org.apache.http.Header;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.tacademy.penthouse.MyApplication;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.RoomData;

public class NetworkManager {
	public static final String PARAM_COUNT = "COUNT";
	public static final String PARAM_PAGE = "PAGE";
	
	public static final String PARAM_USER_ID = "USER_ID";
	public static final String PARAM_USER_PW = "PASSWORD";
	public static final String PARAM_USER_NICKNAME = "USER_NICKNAME";
	public static final String PARAM_USER_IMG = "USER_IMG";
	
	public static final String PARAM_HOUSE_NAME = "HOUSE_NAME";
	public static final String PARAM_HOUSE_IMG = "HOUSE_IMG";
	public static final String PARAM_HOUSE_INTRO = "HOUSE_INTRO";
	
	public static final String PARAM_ROOM_NAME = "ROOM_NAME";
	public static final String PARAM_ROOM_IMG = "ROOM_IMG";
	public static final String PARAM_ROOM_COLOR = "ROOM_COLOR";
	public static final String PARAM_ROOM_PUBLIC = "ROOM_OPEN_CLOSE";
	public static final String PARAM_ROOM_NO = "ROOM_NO";
	
	public static final String PARAM_ITEM_NO = "PRODUCT_NO";
	public static final String PARAM_CATEGORY_NO = "CATEGORY_NO";
//	public static final String PARAM_SEARCH_QUERY = ""
	
	private static NetworkManager instance;
	AsyncHttpClient client;
	Gson gson;
	
	Handler mHandler;
	
	//Singleton
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
	
	public static final String LOGIN_URL = "서버에서 받을 주소";
	public void getLoginData(Context context, String user_id, String user_password, final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		params.put(PARAM_USER_PW, user_password);	
		
		client.get(context,LOGIN_URL, params,new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
					ResultData rd = gson.fromJson(responseString, ResultData.class);
					listener.onSuccess(rd);
					Log.d("Result Message", rd.result_msg);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	public static final String LOGOUT_URL = "서버에서 받을 주소";
	public void getLogoutData(Context context, String user_id, String user_password, final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		params.put(PARAM_USER_PW, user_password);	
		
		client.get(context,LOGOUT_URL, params,new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ResultData rd = gson.fromJson(responseString, ResultData.class);
				listener.onSuccess(rd);
				Log.d("Result Message", rd.result_msg);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
			
		});
	}
	public static final String LostPW_URL = "서버에서 받을 주소";
	public void getLostPWData(Context context, String user_id, final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		/*
		 * 비번 
		 * 
		 * 
		 */
				
	}
	
	public static final String MDRoomData_URL = "";
	public void getMDRoomData(Context context, final OnResultListener<RoomData> listner){
		//RequestParams params = new RequestParams();
		client.get(context, MDRoomData_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				RoomData rd = gson.fromJson(responseString, classOfT)
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	}
	
	//static class DataTask extends AsyncTask...
	
}
