package com.nakhl.behtarinentekhab.activity;

import com.nakhl.behtarinentekhab.R;
import com.nakhl.behtarinentekhab.accordion.utils.FontUtils;
import com.nakhl.behtarinentekhab.accordion.widget.AccordionView;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class ExplainTabDialog extends Dialog {

	public ExplainTabDialog(Context context) {
		super(context);

		//setTitle("Tabbed Dialog Tester");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_explain_swipe);

		TabHost tabHost = (TabHost) findViewById(R.id.TabHost01);
		tabHost.setup();				
	    
		TabHost.TabSpec spec1 = tabHost.newTabSpec("TabOne");
		spec1.setIndicator("روش انجام",
				context.getResources().getDrawable(R.drawable.ic_launcher));
		spec1.setContent(R.id.textViewStructure);
		tabHost.addTab(spec1);
		
		
		TabHost.TabSpec spec3 = tabHost.newTabSpec("TabThree");
		spec3.setIndicator("درباره آزمون",
				context.getResources().getDrawable(R.drawable.ic_launcher));
		spec3.setContent(R.id.textViewExplain);
		tabHost.addTab(spec3);		

	}	

}
