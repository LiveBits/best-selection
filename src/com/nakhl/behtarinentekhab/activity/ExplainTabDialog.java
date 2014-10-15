package com.nakhl.behtarinentekhab.activity;

import com.nakhl.behtarinentekhab.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TabHost;

public class ExplainTabDialog extends Dialog {

	public ExplainTabDialog(Context context) {
		super(context);

		//setTitle("Tabbed Dialog Tester");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_explain_swipe);

		TabHost tabHost = (TabHost) findViewById(R.id.TabHost01);
		tabHost.setup();

		TabHost.TabSpec spec2 = tabHost.newTabSpec("TabTwo");
		spec2.setIndicator("روش انجام",
				context.getResources().getDrawable(R.drawable.ic_launcher));
		spec2.setContent(R.id.textViewStructure);
		tabHost.addTab(spec2);
		
		TabHost.TabSpec spec1 = tabHost.newTabSpec("TabOne");
		spec1.setIndicator("درباره آزمون",
				context.getResources().getDrawable(R.drawable.ic_launcher));
		spec1.setContent(R.id.textViewExplain);
		tabHost.addTab(spec1);		

	}

}
