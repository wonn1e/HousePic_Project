package com.tacademy.penthouse.editimgdialog;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.UserData;

public class EditImgActivity extends Activity {
	TextView bringCamera, bringGallery, deleteImg;
	File mSavedFile;
	public static final int REQUEST_CODE_CROP = 0;
	public static final String PARAM_RESULT = "outputFormat";
	UserData uData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.edit_img_layout);
		bringCamera = (TextView)findViewById(R.id.bring_from_camera);
		bringGallery = (TextView)findViewById(R.id.bring_from_gallery);
		deleteImg = (TextView)findViewById(R.id.delete_img);
		
		
		//Sample2Crop
		bringCamera.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra("crop", "circle");
				i.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
				i.putExtra(PARAM_RESULT, Bitmap.CompressFormat.JPEG.toString());
				startActivityForResult(i, REQUEST_CODE_CROP);
				finish();
			}
		});

		bringGallery.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				i.setType("image/*");
				i.putExtra("crop", "true");
				i.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
				i.putExtra(PARAM_RESULT, Bitmap.CompressFormat.JPEG.toString());
				startActivityForResult(i, REQUEST_CODE_CROP);
				finish();
			}
		});

		deleteImg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.putExtra(PARAM_RESULT, R.drawable.tulips);
				finish();
			}
		});
	}

	private Uri getTempUri(){
		mSavedFile = new File(Environment.getExternalStorageDirectory(), "temp_"+System.currentTimeMillis()/1000);
		return Uri.fromFile(mSavedFile);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("filename", mSavedFile.getAbsolutePath());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode ==REQUEST_CODE_CROP && resultCode == RESULT_OK){
			Bitmap bm = BitmapFactory.decodeFile(mSavedFile.getAbsolutePath());
			(uData.user_img) = bm.toString();
		}
	}
}
