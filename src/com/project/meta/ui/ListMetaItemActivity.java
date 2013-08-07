package com.project.meta.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.project.meta.R;
import com.project.meta.model.Meta;

public class ListMetaItemActivity extends android.support.v4.app.FragmentActivity{
	Meta meta;
	TextView textName;
	TextView textDescription;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_meta_item);

		meta = (Meta) getIntent().getSerializableExtra("meta");
		
		if(meta != null) {
			textName = (TextView)findViewById(R.id.text_name);
			textDescription = (TextView)findViewById(R.id.text_description);
			
			textName.setText(meta.getName());
			textDescription.setText(meta.getDescription());
			
			if(meta.getOrigin() != null && !meta.getOrigin().isEmpty()) {
//				SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment);
//				GoogleMap map = mapFragment.getMap();
				
			}
		}
	}

}
