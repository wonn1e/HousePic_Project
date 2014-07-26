package com.tacademy.penthouse.member;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.tacademy.penthouse.R;

public class LogInFragment extends DialogFragment {
//	@Override
//	@NonNull
//	public Dialog onCreateDialog(Bundle savedInstanceState) {
//		AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
//		builder.setTitle("·Î±×ÀÎ");
//		
//		
//		
//		return super.onCreateDialog(savedInstanceState);
//	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.EditImgDialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog d = getDialog();
		d.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		
		View v = inflater.inflate(R.layout.log_in_dialog_fragment, container,false);
		Button find_password = (Button)v.findViewById(R.id.find_password_btn);
		find_password.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				Fragment old = getFragmentManager().findFragmentByTag("dialog");
				if(old != null){
					ft.remove(old);
				}
				ft.addToBackStack(null);
				FindPasswordFragment f = new FindPasswordFragment();
				f.show(ft,"dialog");
			}
		});
		return v;
	}
}
