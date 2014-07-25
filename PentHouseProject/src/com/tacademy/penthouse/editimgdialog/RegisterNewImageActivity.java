package com.tacademy.penthouse.editimgdialog;

import java.io.File;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.RoomData;

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

public class RegisterNewImageActivity extends Activity {
	TextView bringCamera, bringGallery;
	File mSavedFile;
	public static final int REQUEST_CODE_CROP = 0;
	public static final String PARAM_RESULT = "outputFormat";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_img_layout);
		bringCamera = (TextView)findViewById(R.id.bring_from_camera);
		bringGallery = (TextView)findViewById(R.id.bring_from_gallery);
		
		Intent i = getIntent();

		//Sample2Crop
		bringCamera.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				i.setType("image/*");
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

/*	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode ==REQUEST_CODE_CROP && resultCode == RESULT_OK){
			Bitmap bm = BitmapFactory.decodeFile(mSavedFile.getAbsolutePath());
			//(uData.user_img) = bm.toString();
			//��ø� �ּ�^^;;
		}
	}*/
}
