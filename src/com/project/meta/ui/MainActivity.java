package com.project.meta.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.project.meta.R;

public class MainActivity extends Activity {

	private Button btnAdd;
	private Button btnList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.btnAdd = (Button)findViewById(R.id.btnAdd);
		this.btnList = (Button)findViewById(R.id.btnList);
		
		this.btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(v.getContext(), AddMetaActivity.class);
				startActivity(it);
			}
		});
		
		this.btnList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(v.getContext(), ListMetasActivity.class);
				startActivity(it);
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}


