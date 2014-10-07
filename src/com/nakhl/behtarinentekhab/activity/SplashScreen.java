package com.nakhl.behtarinentekhab.activity;

import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nakhl.behtarinentekhab.R;

/**
 * Main application activity.
 * 
 * @author Maciej Laskowski
 * 
 */
public class SplashScreen extends FullScreenActivity {

	/** Intro textview. */
	//@InjectView(R.id.imgLogo)
	private ImageView ivLogo;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
				
		showAnimation();
		
	}


	private void showAnimation() {		
		
		Animation fadeLogo = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.splash_fading);
		
		fadeLogo.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
								
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		});
		
		ivLogo.setAnimation(fadeLogo);
	}
	

}
