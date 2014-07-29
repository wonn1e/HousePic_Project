package com.tacademy.penthouse.itemlike;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;

public class CreateNewRoomActivity extends Activity{
	public final static String PARAM_ITEM_IN_NEW_ROOM ="item in new room";

	UserData myData;
	ItemData iData;
	RoomData newRoomData;
	ImageView new_room_img;
	EditText room_name;
	CheckBox isPrivate;
	ImageView color1, color2, color3, color4, color5, color6, color7, color8, color9, color10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_room);
		Intent i = getIntent();
		iData = i.getParcelableExtra("iData");
		myData = i.getParcelableExtra("myData");

		room_name = (EditText)findViewById(R.id.get_new_name);
		new_room_img = (ImageView)findViewById(R.id.new_room_img);
		isPrivate = (CheckBox)findViewById(R.id.check_private);
		color1 = (ImageView)findViewById(R.id.color1);
		color2 = (ImageView)findViewById(R.id.color2);
		color3 = (ImageView)findViewById(R.id.color3);
		color4 = (ImageView)findViewById(R.id.color4);
		color5 = (ImageView)findViewById(R.id.color5);
		color6 = (ImageView)findViewById(R.id.color6);
		color7 = (ImageView)findViewById(R.id.color7);
		color8 = (ImageView)findViewById(R.id.color8);
		color9 = (ImageView)findViewById(R.id.color9);
		color10 = (ImageView)findViewById(R.id.color10);
		Button btn = (Button)findViewById(R.id.room_create_btn);

		new_room_img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//room name is given
				if((String)room_name.getText().toString()!= null && 
						!((String)room_name.getText().toString()).equals("")){
					//               //room img is selected
					//   if(new_room_img != null){
					//room color is selected;;
					/*
					 * 
					 * 
					 * 
					 */

					newRoomData = new RoomData();
					newRoomData.user_num = myData.user_num;
					newRoomData.room_name = room_name.getText().toString();
					//if(isPrivate.){
					//newRoomData.isPublic = false;
					//}
					//         newRoomData.room_num = 
					//         newRoomData.room_img = 
					//         newRoomData.room_color_theme = 


					if(iData != null){
						//now unlike
						if(iData.item_like){
							iData.item_like = false;
							iData.likeCnt--;
							Toast.makeText(CreateNewRoomActivity.this, "unlike in CreateNewRoom", Toast.LENGTH_SHORT).show();
						}
						//now like
						else{
							iData.item_like = true;
							iData.likeCnt++;
							Toast.makeText(CreateNewRoomActivity.this, "like in CreateNewRoom", Toast.LENGTH_SHORT).show();
						}

					}
					/*
					 * 
					 * item data & room Data update! to ¼­¹ö
					 * 
					 */

					Intent data = new Intent();
					data.putExtra(PARAM_ITEM_IN_NEW_ROOM, iData);
					setResult(Activity.RESULT_OK, data);
					finish();
				}
				//}
			}
		});
	}
}
