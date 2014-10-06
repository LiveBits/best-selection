package com.nakhl.behtarinentekhab.activity;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.nakhl.behtarinentekhab.R;

public class AboutActivity extends FullScreenActivity {

	//about activity
	
	/** About textview. */
	@InjectView(R.id.tvAbout)
	private TextView tvAbout;
	
	/** Group logo textview. */
	@InjectView(R.id.tvGroupLogo)
	private TextView tvGroupLogo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);		
		
		//catch from 'chehelhadis'
		String strLogo = "گروه برنامه نویسی نخل";
		tvGroupLogo.setText(strLogo);
		//tvGroupLogo.setTypeface(font);
		String AboutText = "برنامه نویسان:" + "\n\t\t\t" + "احمد خلیل فر"
				+ "\n\t\t\t" + "محمد مختاری";
		tvAbout.setText(AboutText);
		//tvAbout.setTypeface(font);
	}
}
