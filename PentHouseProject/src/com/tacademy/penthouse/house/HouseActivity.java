package com.tacademy.penthouse.house;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.editimgdialog.EditImgActivity;
import com.tacademy.penthouse.entity.HouseData;
import com.tacademy.penthouse.entity.UserData;

public class HouseActivity extends FragmentActivity {
	private static final int FIELD_MINE = 0;
	private static final int FIELD_OTHER = 1;

	public static final String TAG_NICKNAME = "nickname";
	public static final String TAG_HOUSENAME = "housename";
	public static final String TAG_HOUSEINTRO = "houseintro";
	public static final int REQUEST_CODE_EDITIMG = 0;
	
	TextView user_nickname, house_name, house_intro;
	ImageView user_img, house_img, edit_btn;
	UserData uData = new UserData(10, "asdf", "nickname", "pw", 100, 100, "dddd");
	HouseData hData = new HouseData(10, 12, "nickname's house", "HOUSE!!", "dddd");

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

	private void init(){
		/*user_nickname.setText(uData.user_nickname);
		house_name.setText(hData.house_name);
		house_intro.setText(hData.house_intro);
		user_img.setImageResource(R.drawable.ic_launcher);	///////
		house_img.setImageResource(R.drawable.ic_launcher);*/
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.house_layout);

		init();

		int code;
		if(uData.user_num == hData.user_num) code = FIELD_MINE;
		else code = FIELD_OTHER;

		user_nickname = (TextView)findViewById(R.id.user_nickname);
		house_name = (TextView)findViewById(R.id.house_name);
		house_intro = (TextView)findViewById(R.id.house_info);
		user_img = (ImageView)findViewById(R.id.user_img);
		house_img = (ImageView)findViewById(R.id.house_img);
		edit_btn = (ImageView)findViewById(R.id.edit_btn);	

		if(code == FIELD_MINE){
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
											uData.user_nickname = message;
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
											hData.house_name = name;
											//Toast.makeText(HouseActivity.this, "current house name: " + hData.house_name, Toast.LENGTH_SHORT).show();
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
											hData.house_intro = intro;
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
		}else{
			edit_btn.setVisibility(View.GONE);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == REQUEST_CODE_EDITIMG && resultCode == EditImgActivity.RESULT_OK){
			String imgBitmap = data.getStringExtra(EditImgActivity.PARAM_RESULT);
			Toast.makeText(HouseActivity.this, "new img: " + imgBitmap, Toast.LENGTH_SHORT).show();
		}
		
	}
}
