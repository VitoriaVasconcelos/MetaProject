package com.project.meta.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.project.meta.R;
import com.project.meta.model.Meta;

public class ListMetaItemActivity extends FragmentActivity {
	Meta meta;
	TextView textName;
	TextView textDescription;
	TextView textOrigin;
	TextView textDestination;
	GoogleMap map;
	Button routeButton;
	public static final String ORIGIN = "origin";
	public static final String DESTINATION = "destination";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_meta_item);
		
		meta = (Meta) getIntent().getSerializableExtra("meta");
		
		routeButton = (Button)findViewById(R.id.buttonRoute);
		if(meta.getOrigin() != null && !meta.getOrigin().isEmpty()) {
			textOrigin = (TextView)findViewById(R.id.text_origin);
		}
		
		if(meta.getDestination() != null && !meta.getDestination().isEmpty()) {
			textDestination = (TextView)findViewById(R.id.text_destination);
		}
		
		if(textDestination != null && textOrigin != null) {
			routeButton.setVisibility(Button.VISIBLE);
		} else {
			routeButton.setVisibility(Button.GONE);
		}
		routeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(textDestination != null && textOrigin != null) { 
					Intent i = new Intent("com.project.meta.MAP");
					i.putExtra(ORIGIN, meta.getOrigin());
					i.putExtra(DESTINATION, meta.getDestination());
					startActivity(i);
				}
				
			}
		});
		
		if(meta != null) {
			textName = (TextView)findViewById(R.id.text_name);
			textDescription = (TextView)findViewById(R.id.text_description);
			
			textName.setText(meta.getName());
			textDescription.setText(meta.getDescription());
			if(textOrigin != null)
				textOrigin.setText(meta.getOrigin());
			
			if(textDestination != null)
				textDestination.setText(meta.getDestination());
		}
	}
	
}
