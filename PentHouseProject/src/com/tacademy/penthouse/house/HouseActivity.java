package com.tacademy.penthouse.house;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.penthouse.HeaderGridView;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.editimgdialog.EditImgActivity;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.itemlike.CreateNewRoomActivity;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.neighbor.NeighborListActivity;
import com.tacademy.penthouse.room.UserRoomInfoActivity;

public class HouseActivity extends FragmentActivity {
	public static final String TAG_NICKNAME = "nickname";
	public static final String TAG_HOUSENAME = "housename";
	public static final String TAG_HOUSEINTRO = "houseintro";
	public static final int REQUEST_CODE_EDITIMG = 0;
	public static final int REQEUST_MAKE_NEW_ROOM = 1;
	public static final String PARAM_USER_DATA = "uData";
	public static final String PARAM_MY_DATA = "myData";

	public static final int ID_MYHOUSE = 0;

	//StaggeredGridView house_room_gridView;	
	HeaderGridView house_room_gridView;	
	TextView user_nickname, house_name, house_intro, house_room_list;
	ImageView user_img, house_img, edit_btn;
	ImageLoader loader;
	DisplayImageOptions userOptions, houseOptions;

	UserData uData;
	UserData myData;
	String uDataId;

	RoomAdapter roomAdapter;
	MyRoomAdapter myRoomAdapter;
//	ArrayList<RoomData> roomsResult;
	UserRoomsResult userRoomsResult;

/*	String[] img = {"http://tv02.search.naver.net/ugc?t=252x448&q=http://imgnews.naver.com/image/022/2014/07/23/20140723003688_0_99_20140723213004.jpg",
			"http://tv02.search.naver.net/ugc?t=252x448&q=http://blogfiles.naver.net/20140529_81/iys0610_1401366281000HoExC_JPEG/2014-05-29_21%3B12%3B57.jpg",
	"http://tv02.search.naver.net/ugc?t=252x448&q=http://imgnews.naver.com/image/009/2013/05/30/20130529_1369812840..jpg_59_20130530084634.jpg"};
	//	final RoomData[] rData = {
	//			new RoomData(1,1,"user1 room1", img[1], "room1",true, "red"),
	//			new RoomData(2,1,"user2 room1", img[2], "room2",true, "red"),
	//			new RoomData(1,3,"user1 room3", img[3], "room3",true, "red"),
	//	};
*/

	//수정 시 click인지 아닌지
	boolean isClicked;
	private void setClicked(boolean clicked){
		if(isClicked != clicked){
			isClicked = clicked;
			drawIsClick();
		}
		else{
			isClicked = !clicked;
			drawIsClick();
		}
	}
	//click일때 아닐때 그림바꾸기
	private void drawIsClick(){
		if(isClicked){
			edit_btn.setImageResource(android.R.drawable.checkbox_on_background);
		}else{
			edit_btn.setImageResource(android.R.drawable.checkbox_off_background);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.house_layout);
		//myData = new UserData(2, "bb", "bb", "bb", 2, 2, R.drawable.tulips, "bb", "bb", "bb");
		//	hData =  new HouseData(10, 12, "nickname's house", "HOUSE!!", "dddd");
		//	hData = new HouseData();
		
		Intent i = getIntent();
		uDataId = i.getStringExtra("uData");
		//	myData = i.getParcelableExtra(PARAM_MY_DATA);
		//	hData = i.getParcelableExtra("hData");

		View v = getLayoutInflater().inflate(R.layout.header_view_house_layout, null);
		user_nickname = (TextView)v.findViewById(R.id.userNicknameHouse);
		house_name = (TextView)v.findViewById(R.id.houseNameHouse);
		house_intro = (TextView)v.findViewById(R.id.houseInfoHouse);
		user_img = (ImageView)v.findViewById(R.id.userImgHouse);
		house_img = (ImageView)v.findViewById(R.id.houseImgHouse);
		edit_btn = (ImageView)v.findViewById(R.id.editHouseBtn);	
		house_room_list = (TextView)v.findViewById(R.id.house_room_list);
		house_room_gridView = (HeaderGridView)findViewById(R.id.header_grid_view);
		house_room_gridView.addHeaderView(v);
		roomAdapter = new RoomAdapter(this);
		loader = ImageLoader.getInstance();
		userOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(100))
		.build();
		houseOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		
		Button btn = (Button)v.findViewById(R.id.followingBtnHouse);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(HouseActivity.this, NeighborListActivity.class);
				i.putExtra(NeighborListActivity.PARAM_CURRENT_TAB, 0);//uData);
				startActivity(i);
			}
		});

		btn = (Button)v.findViewById(R.id.followerBtnHouse);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(HouseActivity.this, NeighborListActivity.class);
				i.putExtra(NeighborListActivity.PARAM_CURRENT_TAB, 1);//uData);
				startActivity(i);
			}
		});


		house_room_gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position != userRoomsResult.result.rooms.size()){
					Intent i = new Intent(HouseActivity.this, UserRoomInfoActivity.class);
					i.putExtra("rData", userRoomsResult.result.rooms.get(position).room_num);
					startActivity(i);
				}
			}
		});

		edit_btn.setVisibility(View.GONE);



		/*	
		if(myData.user_num == uData.user_num){
			myRoomAdapter = new MyRoomAdapter(this);
			house_room_gridView.setAdapter(myRoomAdapter);
//			for(int j=0; j<rData.length; j++){
//				myRoomAdapter.add(rData[j]);
//			}
			house_room_gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					//when it's + img
					if(myRoomAdapter.getItemViewType(position) == 0){

						Toast.makeText(HouseActivity.this, "create new room tab1", Toast.LENGTH_SHORT).show();
						Intent i = new Intent(HouseActivity.this, CreateNewRoomActivity.class);
						//pass on myData (UserData) 
						i.putExtra("myData", myData);
						startActivityForResult(i, REQEUST_MAKE_NEW_ROOM);
						onResume();

					}else{

						Intent i = new Intent(HouseActivity.this, MyRoomInfoActivity.class);
						//i.putExtra("rData", rData[position]);
						startActivity(i);
					}
				}				
			});

			edit_btn.setVisibility(View.VISIBLE);
			edit_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					setClicked(isClicked);

					//edit
					if(isClicked){
						Toast.makeText(HouseActivity.this, "수정이 가능합니다", Toast.LENGTH_SHORT).show();
						user_nickname.setEnabled(true);
						user_nickname.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {

								EditUserNickname f = new EditUserNickname();
								f.setOnReceiveMessageListener(new EditUserNickname.OnReceiveMessageListener() {

									@Override
									public void onReceiveMessage(String message) {
										if(message != null && !message.equals("")){
											user_nickname.setText(message);
											myData.user_nickname = message;
											//Toast.makeText(HouseActivity.this, "current nickname: " + uData.user_nickname, Toast.LENGTH_SHORT).show();
										}
									}
								});
								f.show(getSupportFragmentManager(), TAG_NICKNAME);
							}
						});

						house_name.setEnabled(true);
						house_name.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								EditHouseName f = new EditHouseName();
								f.setOnReceiveHousenameListener(new EditHouseName.OnReceiveHousenameListener() {

									@Override
									public void onReceiveHousename(String name) {
										if(name != null && !name.equals("")){
											house_name.setText(name);
											myData.house_name = name;
										}
									}
								});
								f.show(getSupportFragmentManager(), TAG_HOUSENAME);

							}
						});
						house_intro.setEnabled(true);
						house_intro.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								EditHouseIntro f = new EditHouseIntro();
								f.setOnReceiveHouseintroListener(new EditHouseIntro.OnReceiveHouseintroListener() {

									@Override
									public void onReceiveHousename(String intro) {
										if(intro != null && !intro.equals("")){
											house_intro.setText(intro);
											myData.house_intro = intro;
											//Toast.makeText(HouseActivity.this, "current house name: " + hData.house_intro, Toast.LENGTH_SHORT).show();
										}
									}
								});
								f.show(getSupportFragmentManager(), TAG_HOUSEINTRO);
							}
						});
						user_img.setEnabled(true);
						user_img.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent i = new Intent(HouseActivity.this, EditImgActivity.class);
								startActivityForResult(i, REQUEST_CODE_EDITIMG);
							}
						});
						house_img.setEnabled(true);
						house_img.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub

							}
						});
					}


					else{
						user_nickname.setEnabled(false);						
						house_name.setEnabled(false);
						house_intro.setEnabled(false);
						user_img.setEnabled(false);
						house_img.setEnabled(false);
					}
				}
			});
		}else{//User != My
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			roomAdapter = new RoomAdapter(this);
			house_room_gridView.setAdapter(roomAdapter);
//			for(int j=0; j<rData.length; j++){
//				roomAdapter.add(rData[j]);
//				
//			}
				roomAdapter.add(null);
			house_room_gridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					if(position != 0){
						Intent i = new Intent(HouseActivity.this, UserRoomInfoActivity.class);
//						i.putExtra("rData", rData[position]);
						startActivity(i);
					}
				}
			});

			edit_btn.setVisibility(View.GONE);
		} */
		
		initData();
	}



	private void initData(){
		//user_img.setImageResource(uData.user_img);
		//house_img.setImageResource(hData.house_img);
		/*if(uData.user_num == uData.user_num){
			house_room_list.setText("나의 방 목록");
		}else{
			house_room_list.setText(uData.user_nickname + "의 방 목록");
		}*/
		NetworkManager.getInstance().getUserInfoData(HouseActivity.this, uDataId, new NetworkManager.OnResultListener<UserRoomsResult>() {

			@Override
			public void onSuccess(UserRoomsResult result) {
				uData = result.result.user;
				//roomsResult = result.result.rooms;
				userRoomsResult = result;
				
				user_nickname.setText(uData.user_nickname);
				house_name.setText(uData.house_name);
				house_intro.setText(uData.house_intro);
				house_room_list.setText(uData.user_nickname + "의 방 목록");
				loader.displayImage(result.result.user.user_img_url, user_img, userOptions);
				loader.displayImage(result.result.user.house_img_url , house_img, houseOptions);

				roomAdapter.put(result.result);
				house_room_gridView.setAdapter(roomAdapter);
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(HouseActivity.this, "fail in ItemInfo", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(requestCode == REQUEST_CODE_EDITIMG && resultCode == EditImgActivity.RESULT_OK){
			String imgBitmap = data.getStringExtra(EditImgActivity.PARAM_RESULT);
			Toast.makeText(HouseActivity.this, "new img: " + imgBitmap, Toast.LENGTH_SHORT).show();
		}

		if(requestCode == REQEUST_MAKE_NEW_ROOM && resultCode == CreateNewRoomActivity.RESULT_OK){
			Toast.makeText(HouseActivity.this, "item in new room back in HouseActivity", Toast.LENGTH_SHORT).show();
		}

	}
}
