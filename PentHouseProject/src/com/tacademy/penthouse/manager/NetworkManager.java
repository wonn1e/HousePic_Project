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
import com.tacademy.penthouse.entity.ItemItemsResult;
import com.tacademy.penthouse.entity.ItemsResult;
import com.tacademy.penthouse.entity.RoomsResult;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.RoomItemsResult;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.entity.UsersResult;

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
		//client.setTimeout(30000);
		gson = new Gson();
	}

	public interface OnResultListener<T>{
		public void onSuccess(T result);
		public void onFail(int code);
	}

	public static final String LOGIN_URL = "로그인";
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
	public static final String LOGOUT_URL = "로그아웃";
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
	public static final String LostPW_URL ="비밀번호 찾기";
	public void getLostPWData(Context context, String user_id, final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		/*
		 * 비번 
		 * 
		 * 
		 */

	}

	public static final String MDRoomData_URL = "http://54.178.158.103/sample/room/viewlist";
	public void getMDRoomData(Context context, final OnResultListener<RoomsResult> listener){
	
		client.get(context, MDRoomData_URL, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				RoomsResult rrd = gson.fromJson(responseString, RoomsResult.class);
				listener.onSuccess(rrd);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});

	}
	
	public static final String ItemRanking_URL = "http://54.178.158.103/product/rank";
	public void getItemRankingResultData(Context context, final OnResultListener<ItemsResult> listener){
		client.get(context, ItemRanking_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ItemsResult rankingResult = gson.fromJson(responseString, ItemsResult.class);
				listener.onSuccess(rankingResult);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String userRanking_URL = "http://54.178.158.103/user/rank";
	public void getUserRankingResultData(Context context, final OnResultListener<UsersResult> listener){
		client.get(context, userRanking_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				UsersResult rankingR = gson.fromJson(responseString, UsersResult.class);
				listener.onSuccess(rankingR);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String UserInfoData_URL = "회원 정보";
	public void getUserInfoData(Context context, String user_id, final OnResultListener<UserRoomsResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		
		client.get(context, UserInfoData_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				UserRoomsResult userR = gson.fromJson(responseString, UserRoomsResult.class);
				listener.onSuccess(userR);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String Edit_MyData_URL = "회원 정보 수정 ";
	public void getEditMyData(Context context, String user_id, String user_nickname, String user_password,
				String user_img_url, String house_name, String house_img_url, String house_intro,
				final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		params.put(PARAM_USER_NICKNAME, user_nickname);
		params.put(PARAM_USER_PW, user_password);
		params.put(PARAM_USER_IMG, user_img_url);
		params.put(PARAM_HOUSE_NAME, house_name);
		params.put(PARAM_HOUSE_IMG, house_img_url);
		params.put(PARAM_HOUSE_INTRO, house_intro);
		

		client.get(context, Edit_MyData_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ResultData newData = gson.fromJson(responseString, ResultData.class);
				listener.onSuccess(newData);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	
	
	public static final String Follower_URL = "Follower한 사용자";
	public void getFollowerResultData(Context context, String user_id, final OnResultListener<UsersResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		
		client.get(context, Follower_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				UsersResult follower_result= gson.fromJson(responseString, UsersResult.class);
				listener.onSuccess(follower_result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String Following_URL = "Following한 사용자";
	public void getFollowingResultData(Context context, String user_id, final OnResultListener<UsersResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		
		client.get(context, Following_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				UsersResult following_users = gson.fromJson(responseString, UsersResult.class);
				listener.onSuccess(following_users);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String ItemInfo_URL = "http://54.178.158.103/product/:productNo/viewDetails";
	public void getItemInfoResultData(Context context, int item_num, final OnResultListener<ItemItemsResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_ITEM_NO, item_num);
		
		client.get(context, ItemInfo_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ItemItemsResult result = gson.fromJson(responseString, ItemItemsResult.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}

	public static final String CategorySearch_URL = "카테고리 상품 검색";
	public void getCategorySearchResultData(Context context, String keyword, final OnResultListener<ItemsResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_SEARCH_QUERY, keyword);
		
		client.get(context, TextSearch_URL, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ItemsResult category_result = gson.fromJson(responseString, ItemsResult.class);
				listener.onSuccess(category_result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
				String responseString, Throwable throwable) {
				listener.onFail(statusCode);

			}
			
		});
	}
	
	public static final String TextSearch_URL = "텍스트 상품 검색";
	public void getTextSearchResultData(Context context, String keyword, final OnResultListener<ItemsResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_SEARCH_QUERY, keyword);
		
		client.get(context, TextSearch_URL, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ItemsResult item_result = gson.fromJson(responseString, ItemsResult.class);
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

	
	public static final String EveryoneRoomData_URL = "전체 사용자들의 방 구경";
	public void getEveryoneRoomData(Context context, final OnResultListener<RoomsResult> listener){
		client.get(context, EveryoneRoomData_URL,new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				RoomsResult rrd = gson.fromJson(responseString, RoomsResult.class);
				listener.onSuccess(rrd);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
		
	}
	
	public static final String FriendsRoomData_URL = "특정 사용자의 친구들 방 구경";
	public void getFriendRoomData(Context context,String user_id, final OnResultListener<RoomsResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		
		client.get(context, FriendsRoomData_URL, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				RoomsResult rrd = gson.fromJson(responseString, RoomsResult.class);
				listener.onSuccess(rrd);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String makeRoom_URL = "방 추가";
	public void getMakeRoom(Context context, String user_id, String room_name, String room_img, 
							String room_color, boolean ispublic, final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		params.put(PARAM_ROOM_NAME,room_name);
		params.put(PARAM_ROOM_IMG, room_img);
		params.put(PARAM_ROOM_COLOR, room_color);
		params.put(PARAM_ROOM_ISPUBLIC, ispublic);
		
		client.get(context, makeRoom_URL,params, new TextHttpResponseHandler() {
			
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

	public static final String getDeleteRoom_URL = "방 삭제";
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

	public static final String getEditRoom_URL = "방 수정";
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
	
	public static final String roomInfo_URL = "http://54.178.158.103/user/:userId/room/:roomNo/product/viewlist";
	public void getRoomInfo(Context context, String user_id, int room_num, final OnResultListener<RoomItemsResult> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		params.put(PARAM_ROOM_NO, room_num);
		
		client.get(context, roomInfo_URL,params,new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				RoomItemsResult rd = gson.fromJson(responseString, RoomItemsResult.class);
				listener.onSuccess(rd);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public static final String addItem_URL = "방 안에 상품 추가";
	public void getAddItem(Context context, String user_id, int room_num, int item_num, final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		params.put(PARAM_ROOM_NO, room_num);
		params.put(PARAM_ITEM_NO, item_num);
		
		client.get(context, addItem_URL, params, new TextHttpResponseHandler() {
			
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
	
	public static final String deleteItem_URL = "방 안에 상품 삭제";
	public void getDeleteItem(Context context, String user_id, int room_num, int item_num, final OnResultListener<ResultData> listener){
		RequestParams params = new RequestParams();
		params.put(PARAM_USER_ID, user_id);
		params.put(PARAM_ROOM_NO, room_num);
		params.put(PARAM_ITEM_NO, item_num);
		
		client.get(context, deleteItem_URL, params, new TextHttpResponseHandler() {
			
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
	
	
	
}

//static class DataTask extends AsyncTask...


