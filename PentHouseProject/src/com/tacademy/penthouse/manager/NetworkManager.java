package com.tacademy.penthouse.manager;

import java.util.ArrayList;

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
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ItemInfoResult;
import com.tacademy.penthouse.entity.ItemResult;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.RoomResult;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserResult;

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
	public static final String PARAM_ROOM_ISPUBLIC = "ROOM_OPEN_CLOSE";
	public static final String PARAM_ROOM_NO = "ROOM_NO";

	public static final String PARAM_ITEM_NO = "PRODUCT_NO";
	public static final String PARAM_CATEGORY_NO = "CATEGORY_NO";

	public static final String PARAM_SEARCH_QUERY = "QUERY";	//json규격에서!


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
	public void getMDRoomData(Context context, final OnResultListener<RoomResult> listener){
		//RequestParams params = new RequestParams();
		client.get(context, MDRoomData_URL, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				RoomResult rrd = gson.fromJson(responseString, RoomResult.class);
				listener.onSuccess(rrd);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});

	}
	
	public static final String ItemRanking_URL = " ";
	public void getItemRankingResultData(Context context, final OnResultListener<ItemResult> listener){
		client.get(context, ItemRanking_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ItemResult rankingResult = gson.fromJson(responseString, ItemResult.class);
				listener.onSuccess(rankingResult);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String Following_URL = " ";
	public void getFollowingResultData(Context context, String user_id, final OnResultListener<UserResult> listener){
		client.get(context, Following_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				UserResult following_users = gson.fromJson(responseString, UserResult.class);
				listener.onSuccess(following_users);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String ItemInfo_URL = " ";
	public void getItemInfoResultData(Context context, String item_num, final OnResultListener<ItemInfoResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, item_num);
		
		client.get(context, ItemInfo_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ItemInfoResult result = gson.fromJson(responseString, ItemInfoResult.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}

	public static final String CategorySearch_URL = " ";
	public void getCategorySearchResultData(Context context, String keyword, final OnResultListener<ItemResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_SEARCH_QUERY, keyword);
		client.get(context, TextSearch_URL, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ItemResult category_result = gson.fromJson(responseString, ItemResult.class);
				listener.onSuccess(category_result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
				String responseString, Throwable throwable) {
				listener.onFail(statusCode);

			}
			
		});
	}
	
	public static final String TextSearch_URL = " ";
	public void getTextSearchResultData(Context context, String keyword, final OnResultListener<ItemResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_SEARCH_QUERY, keyword);
		client.get(context, TextSearch_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ItemResult item_result = gson.fromJson(responseString, ItemResult.class);
				listener.onSuccess(item_result);
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}

	//static class DataTask extends AsyncTask...

	
	public static final String EveryoneRoomData_URL = "";
	public void getEveryoneRoomData(Context context, final OnResultListener<RoomResult> listener){
		client.get(context, EveryoneRoomData_URL,new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				RoomResult rrd = gson.fromJson(responseString, RoomResult.class);
				listener.onSuccess(rrd);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
		
	}
	
	public static final String FriendRoomData_URL = "";
	public void getFriendRoomData(Context context,String user_id, final OnResultListener<RoomResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		
		client.get(context, FriendRoomData_URL, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				RoomResult rrd = gson.fromJson(responseString, RoomResult.class);
				listener.onSuccess(rrd);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String getMakeRoom_URL = "";
	public void getMakeRoom(Context context, String user_id, String room_name, String room_img, 
							String room_color, boolean ispublic, final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		params.put(PARAM_ROOM_NAME,room_name);
		params.put(PARAM_ROOM_IMG, room_img);
		params.put(PARAM_ROOM_COLOR, room_color);
		params.put(PARAM_ROOM_ISPUBLIC, ispublic);
		
		client.get(context, getMakeRoom_URL,params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ResultData rd = gson.fromJson(responseString, ResultData.class);
				listener.onSuccess(rd);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}

	public static final String getDeleteRoom_URL = "";
	public void getDeleteRoom(Context context, String user_id, int room_num, final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		params.put(PARAM_ROOM_NO, room_num);
		
		client.get(context, getDeleteRoom_URL,params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ResultData rd = gson.fromJson(responseString, ResultData.class);
				listener.onSuccess(rd);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}

	public static final String getEditRoom_URL = "";
	public void getEditRoom(Context context, String user_id, int room_num, String room_name, String room_img, 
								String room_color,boolean ispublic,final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		params.put(PARAM_ROOM_NO, room_num);
		params.put(PARAM_ROOM_NAME, room_name);
		params.put(PARAM_ROOM_IMG, room_img);
		params.put(PARAM_ROOM_COLOR, room_color);
		params.put(PARAM_ROOM_ISPUBLIC, ispublic);
		
		client.get(context,  getEditRoom_URL, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				// TODO Auto-generated method stub
				
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


