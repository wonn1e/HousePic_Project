package com.tacademy.penthouse.house;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.dialog.EditUserNickname;
import com.tacademy.penthouse.entity.HouseData;
import com.tacademy.penthouse.entity.UserData;

public class HouseActivity extends FragmentActivity {
	private static final int FIELD_MINE = 0;
	private static final int FIELD_OTHER = 1;
	EditUserNickname fragment_nickname;

	public static final String NICKNAME_TAG = "nickname";

	//public static final int REQUEST_CODE_NEWNICKNAME = 0;

	TextView user_nickname, house_name, house_info;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.house_layout);

		int code;
		if(uData.user_num == hData.user_num) code = FIELD_MINE;
		else code = FIELD_OTHER;

		user_nickname = (TextView)findViewById(R.id.user_nickname);
		house_name = (TextView)findViewById(R.id.house_name);
		house_info = (TextView)findViewById(R.id.house_info);
		user_img = (ImageView)findViewById(R.id.user_img);
		house_img = (ImageView)findViewById(R.id.house_img);
		edit_btn = (ImageView)findViewById(R.id.edit_btn);

		fragment_nickname = new EditUserNickname();
		fragment_nickname.setOnReceiveMessageListener(new EditUserNickname.OnReceiveMessageListener() {
			
			@Override
			public void onReceiveMessage(String message) {
				user_nickname.setText(message);
				Toast.makeText(HouseActivity.this, "new nickname", Toast.LENGTH_SHORT).show();
			}
		});		
		
		if(code == FIELD_MINE){
			edit_btn.setVisibility(View.VISIBLE);
			edit_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					setClicked(isClicked);

					//edit
					if(isClicked){
						user_nickname.setEnabled(true);
						user_nickname.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								Fragment f = getSupportFragmentManager().findFragmentByTag(NICKNAME_TAG);
								if (f == null) {
									FragmentTransaction ft = getSupportFragmentManager()
											.beginTransaction();
									ft.replace(R.id.container, fragment_nickname, NICKNAME_TAG);
									ft.commit();
								}
								
								
							/*	fragment_nickname = (EditUserNickname)getSupportFragmentManager().findFragmentByTag(NICKNAME_TAG);

								fragment_nickname.setOnReceiveMessageListener(new EditUserNickname.OnReceiveMessageListener() {

									@Override
									public void onReceiveMessage(String message) {
										user_nickname.setText(message);
										Toast.makeText(HouseActivity.this, "new nickname", Toast.LENGTH_SHORT).show();
									}
								});
*/
								/*Intent i = new Intent(HouseActivity.this, EditUserNickname.class);
								UserData u = new UserData();
								i.putExtra(EditUserNickname.PARAM_ME, u);
								startActivityForResult(i, REQUEST_CODE_NEWNICKNAME);
								 */
							}
						});

						house_name.setEnabled(true);
						house_name.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub

							}
						});
						house_info.setEnabled(true);
						house_info.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub

							}
						});
						user_img.setEnabled(true);
						user_img.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub

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
						house_info.setEnabled(false);
						user_img.setEnabled(false);
						house_img.setEnabled(false);
					}
				}
			});
		}else{
			edit_btn.setVisibility(View.GONE);
		}
	}


}
