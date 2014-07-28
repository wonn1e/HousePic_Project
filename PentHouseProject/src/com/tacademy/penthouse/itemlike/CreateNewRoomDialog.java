package com.tacademy.penthouse.itemlike;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.editimgdialog.EditImgActivity;
import com.tacademy.penthouse.editimgdialog.RegisterNewImageActivity;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.item.ItemInfoActivity;

public class CreateNewRoomDialog extends DialogFragment {
	public static final String PARAM_ITEM_NEW_ROOM = "item data to new room";
	public static final int ID_IMAGE = 0;

	ItemData iData;
	RoomData[] myRoomData;
	RoomData newRoomData;
	ImageView new_room_img;
	TextView roomname_length;
	EditText room_name;
	CheckBox isPublic;
	
	public interface OnRoomCreatedListener{
		public void onRoomCreated(boolean roomCreated);
	}
	
	OnRoomCreatedListener cListener;
	
	public void setOnRoomCreatedListener(OnRoomCreatedListener listener){
		cListener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.HouseDialog);

		Bundle iB = getArguments();
		if(iB != null){
			//iData = (ItemData)iB.getParcelable(PARAM_ITEM_NEW_ROOM);     
			iData = (ItemData)iB.getParcelable(ItemLikeShowListDialog.PARAM_ITEM_DATA);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog d = getDialog();
		d.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		View v = inflater.inflate(R.layout.create_new_room, container, false);
		new_room_img = (ImageView)v.findViewById(R.id.new_room_img);
		new_room_img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//////////////new dialog
				//((ItemInfoActivity)getActivity()).createNewRoom(ID_IMAGE);
				Intent i = new Intent(getActivity(), RegisterNewImageActivity.class);
				startActivity(i);
			}
		});

		roomname_length = (TextView)v.findViewById(R.id.name_length);
		/////////////how?

		room_name = (EditText)v.findViewById(R.id.room_name);
		isPublic = (CheckBox)v.findViewById(R.id.check_public);
		//if()

		Button btn = (Button)v.findViewById(R.id.room_create_btn);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//newRoomData = new RoomData();
				//newRoomData.room_name = (String)room_name.toString();
				//newRoomData.room_img = new_room_img; from ImageVIew to int
				
				///	newRoomData.room_num =
				/*if(         ){                                                                                                                                                                                                                                                )
					newRoomData.isPublic = 
				}
				else{

				}*/
				if(cListener != null)
					cListener.onRoomCreated(true);
				
				dismiss();
				
				/*String name = (String)room_name.getText().toString();
				if(name != null && !name.equals("")){
					Toast.makeText(getActivity(), "new room created", Toast.LENGTH_SHORT).show();
					newRoomData.room_name = name;
					/////////
					dismiss();
				}
				else{
					Toast.makeText(getActivity(), "방 이름을 입력하세요", Toast.LENGTH_SHORT).show();
				}*/
			}
		});
		return v;
	}
}
